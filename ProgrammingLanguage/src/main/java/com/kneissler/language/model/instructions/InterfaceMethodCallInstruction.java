package com.kneissler.language.model.instructions;

import com.kneissler.language.model.InterfaceReference;
import com.kneissler.language.model.ModuleReference;

public class InterfaceMethodCallInstruction extends CallInstruction {

    private final InterfaceReference interfaceReference;

    public InterfaceMethodCallInstruction(String methodName, InterfaceReference interfaceReference) {
        super(methodName);
        this.interfaceReference = interfaceReference;
    }

}
