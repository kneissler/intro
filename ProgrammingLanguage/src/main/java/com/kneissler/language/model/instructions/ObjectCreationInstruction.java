package com.kneissler.language.model.instructions;

import com.kneissler.language.model.Instruction;
import com.kneissler.language.model.InterfaceReference;
import com.kneissler.language.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectCreationInstruction implements Instruction {
    private final InterfaceReference interfaceReference;

    private final Map<String, Type> members;

    private final List<Instruction> objectInstructions = new ArrayList<Instruction>();

    public ObjectCreationInstruction(InterfaceReference interfaceReference, Map<String, Type> members) {
        this.interfaceReference = interfaceReference;
        this.members = members;
    }
}
