package com.kneissler.language.model.instructions;

import com.kneissler.language.model.Instruction;

public class LiteralStringInstruction implements Instruction {

    private final String literal;


    public LiteralStringInstruction(String literal) {
        this.literal = literal;
    }
}
