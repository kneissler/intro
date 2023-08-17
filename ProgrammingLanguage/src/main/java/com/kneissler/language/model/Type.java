package com.kneissler.language.model;

import java.util.List;
import java.util.Set;

public class Type {
    String packageName; // may be null for predefined modules
    String moduleName;
    String interfaceName;
    List<Type> templateArguments;
    Set<TypeModifier> modifiers;
}
