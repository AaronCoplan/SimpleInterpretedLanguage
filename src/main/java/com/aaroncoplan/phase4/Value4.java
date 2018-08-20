package com.aaroncoplan.phase4;

import java.util.Map;

public class Value4 {

    enum Type {
        INT,
        DOUBLE,
        REFERENCE,
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

    public String getValue() {
        return this.value;
    }

    // if this is a reference, return the value it is referring to rather than the reference itself
    public Value4 resolveReference(Map<String, Value4> symbolTable) {
        if(this.getType() != Type.REFERENCE) {
            return this;
        } else {
            Value4 referredValue = symbolTable.get(this.value);
            return referredValue;
        }
    }
}
