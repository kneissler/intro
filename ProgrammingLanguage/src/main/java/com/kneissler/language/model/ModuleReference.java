package com.kneissler.language.model;

import java.util.HashMap;
import java.util.Map;

public class ModuleReference {
    final String[] packagePath; // may be empty for predefined modules

    final String moduleName;

    final Map<String, Type> tyoeMapping = new HashMap<>(); // from referred module template parameters to referring (this) module types

    public ModuleReference(String[] packagePath, String moduleName) {
        this.packagePath = packagePath;
        this.moduleName = moduleName;
    }

    public void addTypeMapping(String key, Type value) {
        if (tyoeMapping.put(key, value) != null)
            throw new RuntimeException("type was specified twice: "+key);
    }
}
