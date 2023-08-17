package com.kneissler.language.model;

public class InterfaceReference {
    final ModuleReference moduleReference;

    final String interfaceName;

    public InterfaceReference(ModuleReference moduleReference, String interfaceName) {
        this.moduleReference = moduleReference;
        this.interfaceName = interfaceName;
    }
}
