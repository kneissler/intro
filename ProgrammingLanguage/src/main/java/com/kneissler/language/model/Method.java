package com.kneissler.language.model;

import java.util.*;

public class Method {

    final MethodScope scope;
    final MethodDeclaration declaration;
    final List<Instruction> instructions = new ArrayList<>();
    final Map<String, Type> localVariables = new HashMap<>();

    public Method(MethodScope scope, MethodDeclaration declaration) {
        this.scope = scope;
        this.declaration = declaration;
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    public void addVariable(String name, Type tyoe) {
        if (localVariables.put(name, tyoe) != null) {
            throw new RuntimeException("variable was put twice: "+name);
        }
    }
}
