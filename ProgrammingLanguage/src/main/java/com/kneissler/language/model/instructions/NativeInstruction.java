package com.kneissler.language.model.instructions;

import com.kneissler.language.model.Instruction;

public class NativeInstruction implements Instruction {

    private final String nativeScript;


    public NativeInstruction(String nativeScript) {
        this.nativeScript = nativeScript;
    }
}
