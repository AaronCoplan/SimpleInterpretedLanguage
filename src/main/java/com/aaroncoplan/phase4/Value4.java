package com.aaroncoplan.phase4;

public class Value4 {

    enum Type {
        INT,
        DOUBLE,
    }

    private final Type type;
    private final String value;

    public Value4(Type type, String value) {
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
