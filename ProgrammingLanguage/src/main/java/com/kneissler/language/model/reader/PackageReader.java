package com.kneissler.language.model.reader;

import com.kneissler.language.model.*;
import com.kneissler.language.model.Module;
import com.kneissler.language.model.Package;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PackageReader {

    private static final String INTERFACES_FOLDER = "interfaces";
    private static final String METHODS_FOLDER = "methods";
    private static final String TYPE_PARAMETERS_FILENAME = "type-parameters";
    private static final String SUPER_INTERFACES_FILENAME = "super-interfaces";
    private static final String MODIFIERS_FILENAME = "modifiers";
    private static final String PARAMETERS_FILENAME = "parameters";
    private static final String RETURNS_FILENAME = "returns";
    private static final String INSTRUCTIONS_FILENAME = "instructions";
    private final Path root;
    
    public PackageReader(Path root) {
        this.root = root;
    }
    
    public Package readPackage(String[] path) {
        Package res = new Package(path);
        Path packagePath = root.resolve(String.join(File.separator, path));
        for (ModuleScope scope : ModuleScope.values()) {
            Path scopePath = packagePath.resolve(scope.toString().toLowerCase());
            forSubFolders(scopePath, moduleDir -> res.addModule(readModule(scope, moduleDir)));
        }
        return res;
    }

    private Module readModule(ModuleScope scope, Path moduleFolder) {
        Module res = new Module(scope, moduleFolder.toFile().getName());
        readLines(moduleFolder.resolve(TYPE_PARAMETERS_FILENAME), res::addTypeParameter);
        for (InterfaceScope s : InterfaceScope.values()) {
            Path scopePath = moduleFolder.resolve(INTERFACES_FOLDER).resolve(s.toString().toLowerCase());
            forSubFolders(scopePath, dir -> res.addInterface(readInterface(res.getName(), s, dir)));
        }
        for (MethodScope s : MethodScope.values()) {
            Path scopePath = moduleFolder.resolve(METHODS_FOLDER).resolve(s.toString().toLowerCase());
            forSubFolders(scopePath, dir -> res.addMethod(readMethod(s, dir)));
        }
        return res;
    }

    private Interface readInterface(String moduleName, InterfaceScope scope, Path dir) {
        Interface res = new Interface(moduleName, dir.toFile().getName(), scope);
        readLines(dir.resolve(SUPER_INTERFACES_FILENAME), s -> res.addSuperInterface(parseInterfaceReference(s)));
        forFiles(dir, file -> res.addMethodDeclaration(readMethodDeclaration(file)));
        return res;
    }

    private InterfaceReference parseInterfaceReference(String string) {
        String[] split = string.split("<");
        if (split.length != 2) {
            throw new RuntimeException("expected < character in interface reference");
        }
        if (split[1].charAt(split[1].length()-1) != '>') {
            throw new RuntimeException("expected > character at end of interface reference");
        }
        String typeArguments = split[1].substring(0, split[1].length()-1);
        String[] path = split[0].split("\\.");
        if (path.length < 2) {
            throw new RuntimeException("Need at least 2 path elements separated by . for interface reference");
        }
        String[] packagePath = Arrays.copyOf(path, path.length-2);
        String moduleName = path[path.length-2];
        String interfaceName = path[path.length-1];
        ModuleReference moduleReference = new ModuleReference(packagePath, moduleName);
        parseTypeArguments(typeArguments, moduleReference);
        return new InterfaceReference(moduleReference, interfaceName);
    }

    private void parseTypeArguments(String spec, ModuleReference moduleReference) {
        List<String> items = new ArrayList<>();
        int lastpos = -1;
        int level = 0;
        for (int p = 0; p < spec.length(); p++) {
            char c = spec.charAt(p);
            if (c == '<') {
                level++;
            }
            if (c == '>') {
                level--;
            }
            if ((c == ',') && (level == 0)) {
                items.add(spec.substring(lastpos+1, p));
                lastpos = p;
            }
        }
        items.add(spec.substring(lastpos+1, spec.length()));
        items.forEach(s -> parseKeyValueType(s, moduleReference::addTypeMapping));
    }

    private Method readMethod(MethodScope scope, Path dir) {
        MethodDeclaration declaration = readMethodDeclaration(dir);
        Method res = new Method(scope, declaration);
        readLines(dir.resolve(INSTRUCTIONS_FILENAME), s -> parseInstruction(res, s.trim()));
        return res;
    }

    private void parseInstruction(Method method, String line) {
        // comment

        // var = "literal"
        // var = InterfaceRef(var1, var2, ...)
        // var1,var2,... = call
        // # native script
    }

    private MethodDeclaration readMethodDeclaration(Path dir) {
        MethodDeclaration res = new MethodDeclaration(dir.toFile().getName());
        readLines(dir.resolve(MODIFIERS_FILENAME), s -> res.addModifier(MethodModifier.valueOf(s)));
        readLines(dir.resolve(PARAMETERS_FILENAME), s -> parseKeyValueType(s, res::addParameter));
        readLines(dir.resolve(RETURNS_FILENAME), s -> parseKeyValueType(s, res::addReturnValue));
        return res;
    }

    private void readLines(Path path, Consumer<String> consumer) {
        try {
            Files.readAllLines(path).forEach(consumer::accept);
        } catch (IOException e) {
            throw new RuntimeException("could not read file: "+path, e);
        }
    }

    private void parseKeyValueType(String keyValue, BiConsumer<String, Type> biConsumer) {
        String[] split = keyValue.split("=");
        if (split.length != 2)
            throw new RuntimeException("expeced key=value, found: "+keyValue);
        String key = split[0].trim();
        String value = split[1].trim();
        biConsumer.accept(key, parseType(value));
    }

    private void forSubFolders(Path folder, Consumer<Path> consumer) {
        for (File file : folder.toFile().listFiles(f -> f.isDirectory())) {
            consumer.accept(file.toPath());
        }
    }

    private void forFiles(Path folder, Consumer<Path> consumer) {
        for (File file : folder.toFile().listFiles(f -> f.isFile())) {
            consumer.accept(file.toPath());
        }
    }

}
