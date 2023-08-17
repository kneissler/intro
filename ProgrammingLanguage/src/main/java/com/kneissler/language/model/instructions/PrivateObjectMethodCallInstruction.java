package com.kneissler.language.model.instructions;

import com.kneissler.language.model.ModuleReference;

public class PrivateObjectMethodCallInstruction extends CallInstruction {

    private final ModuleReference moduleReference;

    public PrivateObjectMethodCallInstruction(String methodName, ModuleReference moduleReference) {
        super(methodName);
        this.moduleReference = moduleReference;
    }

}
