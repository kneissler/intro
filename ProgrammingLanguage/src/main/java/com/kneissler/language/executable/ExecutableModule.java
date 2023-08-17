package com.kneissler.language.executable;

public class ExecutableModule {
    public String name;
    public ExecutableMethod[] methods;

    public ExecutableModule(String name, ExecutableMethod[] methods) {
        this.name = name;
        this.methods = methods;
    }
}
