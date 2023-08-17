package com.kneissler.language.model.instructions;

import com.kneissler.language.model.ModuleReference;

public class ModuleMethodCallInstruction extends CallInstruction {

    private final ModuleReference moduleReference;

    public ModuleMethodCallInstruction(String methodName, ModuleReference moduleReference) {
        super(methodName);
        this.moduleReference = moduleReference;
    }

}
