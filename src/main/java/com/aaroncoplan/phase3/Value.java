package com.aaroncoplan.phase3;

public class Value {

    enum Type {
        INT,
        DOUBLE,
    }

    private final Type type;
    private final String value;

    public Value(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return this.type;
    }

    public int castInt() {
        return Integer.parseInt(this.value);
    }

    public double castDouble() {
        return Double.parseDouble(this.value);
    }
}
