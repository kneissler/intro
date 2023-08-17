package com.kneissler.language.model.instructions;

import com.kneissler.language.model.Instruction;

import java.util.ArrayList;
import java.util.List;

public class CallInstruction implements Instruction {

    private final List<String> inputVariables = new ArrayList<String>();
    private final List<String> outputVariables = new ArrayList<String>();

    private final String methodName;

    public CallInstruction(String methodName) {
        this.methodName = methodName;
    }
}
