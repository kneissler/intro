package com.kneissler.language.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Module {

    final ModuleScope scope; // if internal: no public methods and no public or sealed interfaces defined, module invisible from outside the package


    final String moduleName;

    final List<String> typeParameters = new ArrayList<>(); // the type variables to be specified to determine all types in the module
    final Map<MethodScope, List<Method>> methods;
    final Map<InterfaceScope, List<Interface>> interfaces;

    public Module(ModuleScope scope, String moduleName) {
        this.scope = scope;
        this.moduleName = moduleName;
        methods = new HashMap<>();
        for (MethodScope s : MethodScope.values()) {
            methods.put(s, new ArrayList<>());
        }
        interfaces = new HashMap<>();
        for (InterfaceScope s : InterfaceScope.values()) {
            interfaces.put(s, new ArrayList<>());
        }
    }

    public void addInterface(Interface i) {
        if ((i.scope.equals(InterfaceScope.PUBLIC) || (i.scope.equals(InterfaceScope.SEALED))
                && !scope.equals(ModuleScope.PUBLIC))) {
            throw new RuntimeException("Cannot define PUBLIC or SEALED interfaces in INTERNAL module");
        }
        interfaces.get(i.scope).add(i);
    }

    public void addMethod(Method m) {
        if (m.scope.equals(MethodScope.PUBLIC) && !scope.equals(ModuleScope.PUBLIC)) {
            throw new RuntimeException("Cannot define PUBLIC methods in INTERNAL module");
        }
        methods.get(m.scope).add(m);
    }

    public void addTypeParameter(String parameter) {
        typeParameters.add(parameter);
    }

    public String getName() {
        return moduleName;
    }
}
