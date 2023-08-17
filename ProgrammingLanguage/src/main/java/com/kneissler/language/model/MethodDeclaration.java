package com.kneissler.language.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MethodDeclaration {
    final String name;
    final Set<MethodModifier> modifiers = new HashSet<>();
    final Map<String, Type> parameters = new HashMap<>();
    final Map<String, Type> returnValues = new HashMap<>();

    public MethodDeclaration(String name) {
        this.name = name;
    }

    public void addModifier(MethodModifier modifier) {
        if (!modifiers.add(modifier))
            throw new RuntimeException("method modifier set twice: "+modifier);
    }

    public void addParameter(String name, Type type) {
        if (returnValues.containsKey(name)) {
            throw new RuntimeException("parameter name was used as return value already: "+name);
        }
        if (parameters.put(name, type) != null) {
            throw new RuntimeException("parameter name was used twice: "+name);
        }
    }

    public void addReturnValue(String name, Type type) {
        if (parameters.containsKey(name)) {
            throw new RuntimeException("return value name was used as return parameter already: "+name);
        }
        if (returnValues.put(name, type) != null) {
            throw new RuntimeException("return valu name was used twice: "+name);
        }
    }

}
