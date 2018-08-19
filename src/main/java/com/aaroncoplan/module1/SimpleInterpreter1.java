package com.aaroncoplan.module1;

import com.aaroncoplan.SimpleInterpreter;

import java.util.Stack;

public class SimpleInterpreter1 implements SimpleInterpreter {

    private final Stack<String> stack;

    public SimpleInterpreter1() {
        this.stack = new Stack<String>();
    }

    @Override
    public void run(String code) {
        String[] lines = code.split("\n");
        for(String line : lines) {
            runLine(line);
            stack.clear();
        }
    }

    private void runLine(String line) {
        String[] pieces = line.split(" ");
        for(String piece : pieces) {
            if(isFunction(piece)) {
                executeFunction(piece);
            } else {
                stack.push(piece);
            }
        }
    }

    public boolean isFunction(String piece) {
        if("add".equals(piece)) {
            return true;
        } else if("multiply".equals(piece)) {
            return true;
        } else if("print".equals(piece)) {
            return true;
        } else {
            return false;
        }
    }

    public void executeFunction(String functionName) {
        System.out.format("Executing '%s'", functionName).println();
    }

}
