package com.aaroncoplan.phase1;

import com.aaroncoplan.SimpleInterpreter;

import java.util.Stack;

public class SimpleInterpreter1 implements SimpleInterpreter {

    private final Stack<String> stack;

    public SimpleInterpreter1() {
        this.stack = new Stack<String>();
    }

    @Override
    public void run(String code) {
        // split the code into lines to execute one by one
        String[] lines = code.split("\n");

        // for every line, run it, then clear the stack to "reset" before running the next line
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
        if("add".equals(functionName)) {
            String arg1String = stack.pop();
            String arg2String = stack.pop();
            int arg1 = Integer.parseInt(arg1String);
            int arg2 = Integer.parseInt(arg2String);
            int sum = arg1 + arg2;
            stack.push(String.valueOf(sum));
        } else if("multiply".equals(functionName)) {
            String arg1String = stack.pop();
            String arg2String = stack.pop();
            int arg1 = Integer.parseInt(arg1String);
            int arg2 = Integer.parseInt(arg2String);
            int product = arg1 * arg2;
            stack.push(String.valueOf(product));
        } else if("print".equals(functionName)) {
            System.out.println(stack.pop());
        } else {
            throw new RuntimeException("Unrecognized function " + functionName);
        }
    }

}
