package com.kneissler.language.model;

import java.util.ArrayList;
import java.util.List;

public class Interface {

    final String moduleName;

    final String interfaceName;

    final InterfaceScope scope;

    List<InterfaceReference> superInterfaces = new ArrayList<>();
    List<MethodDeclaration> methodDeclarations = new ArrayList<>();

    public Interface(String moduleName, String interfaceName, InterfaceScope scope) {
        this.moduleName = moduleName;
        this.interfaceName = interfaceName;
        this.scope = scope;
    }

    public void addSuperInterface(InterfaceReference interfaceReference) {
        superInterfaces.add(interfaceReference);
    }

    public void addMethodDeclaration(MethodDeclaration methodDeclaration) {
        methodDeclarations.add(methodDeclaration);
    }

}
