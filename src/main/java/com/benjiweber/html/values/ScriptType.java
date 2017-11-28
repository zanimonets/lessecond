package com.benjiweber.html.values;

public enum ScriptType {
    javascript("text/javascript");

    public String name;
    public String nameA;
    public String nameB;

    ScriptType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
