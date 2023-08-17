package com.kneissler.language.model;

import java.util.ArrayList;
import java.util.List;

public class Package {

    final String[] packagePath;

    final List<Module> modules = new ArrayList<>();

    public Package(String[] packagePath) {
        this.packagePath = packagePath;
    }

    public void addModule(Module m) {
        modules.add(m);
    }
}
