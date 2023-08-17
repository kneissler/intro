package com.kneissler.language.model;

public enum InterfaceScope {
    /** can be seen and extended outside packge */ PUBLIC,
    /** can be seen outside package, can be extended only inside packge */ SEALED,
    /** can be seen and extended only inside packge */ INTERNAL,
    /** can be seen and extended only inside defining module */ PRIVATE
}
