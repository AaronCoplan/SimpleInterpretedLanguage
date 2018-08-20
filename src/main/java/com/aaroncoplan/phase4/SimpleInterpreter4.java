package com.aaroncoplan.phase4;

import com.aaroncoplan.SimpleInterpreter;
import com.aaroncoplan.phase4.Value4;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SimpleInterpreter4 implements SimpleInterpreter {

    private final Stack<Value4> stack;
    private final Map<String, Value4> symbolTable;

    public SimpleInterpreter4() {
        this.stack = new Stack<Value4>();
        this.symbolTable = new HashMap<String, Value4>();
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
        // split the line into pieces using a space as the delimiter
        // ex. "1 2 add" -> ["1", "2", "add"]
        String[] pieces = line.split(" ");

        // first check if each piece is a function
        // if so, execute it
        // otherwise, push the piece to the stack
        for(String piece : pieces) {
            if(isFunction(piece)) {
                executeFunction(piece);
            } else if (isVariableName(piece)) {
                stack.push(new Value4(Value4.Type.REFERENCE, piece));
            } else {
                try {
                    Integer.parseInt(piece);
                    stack.push(new Value4(Value4.Type.INT, piece));
                } catch (NumberFormatException e) {
                    try {
                        Double.parseDouble(piece);
                        stack.push(new Value4(Value4.Type.DOUBLE, piece));
                    } catch (NumberFormatException e2) {
                        throw new RuntimeException("[ERROR] Invalid argument, must be a number!");
                    }
                }
            }
        }
    }

    public boolean isVariableName(String piece) {
        return piece.startsWith("$");
    }

    // returns true if the piece is a function
    // ex. "add" would return true, and "1" would return false
    public boolean isFunction(String piece) {
        if("add".equals(piece)) {
            return true;
        } else if("multiply".equals(piece)) {
            return true;
        } else if("print".equals(piece)) {
            return true;
        } else if("set".equals(piece)) {
            return true;
        } else {
            return false;
        }
    }

    // carry out the actual execution of the function
    // often involves mutating the stack
    // return value (if there is one) will be pushed onto the stack
    public void executeFunction(String functionName) {
        if("add".equals(functionName)) {
            Value4 arg1 = stack.pop().resolveReference(symbolTable);
            Value4 arg2 = stack.pop().resolveReference(symbolTable);

            if(arg1.getType() == Value4.Type.INT && arg2.getType() == Value4.Type.INT) {
                int sum = arg1.castInt() + arg2.castInt();
                stack.push(new Value4(Value4.Type.INT, String.valueOf(sum)));
            } else if(arg1.getType() == Value4.Type.DOUBLE && arg2.getType() == Value4.Type.DOUBLE) {
                double sum = arg1.castDouble() + arg2.castDouble();
                stack.push(new Value4(Value4.Type.DOUBLE, String.valueOf(sum)));
            } else if((arg1.getType() == Value4.Type.INT && arg2.getType() == Value4.Type.DOUBLE) || (arg1.getType() == Value4.Type.DOUBLE && arg2.getType() == Value4.Type.INT)) {
                double sum = arg1.castDouble() + arg2.castDouble();
                stack.push(new Value4(Value4.Type.DOUBLE, String.valueOf(sum)));
            } else {
                // raise an exception as at least one of the arguments is not a double or int
                throw new RuntimeException("[ERROR] Invalid argument for 'add' function, must be a number!");
            }
        } else if("multiply".equals(functionName)) {
            Value4 arg1 = stack.pop().resolveReference(symbolTable);
            Value4 arg2 = stack.pop().resolveReference(symbolTable);

            if(arg1.getType() == Value4.Type.INT && arg2.getType() == Value4.Type.INT) {
                int product = arg1.castInt() * arg2.castInt();
                stack.push(new Value4(Value4.Type.INT, String.valueOf(product)));
            } else if(arg1.getType() == Value4.Type.DOUBLE && arg2.getType() == Value4.Type.DOUBLE) {
                double product = arg1.castDouble() * arg2.castDouble();
                stack.push(new Value4(Value4.Type.DOUBLE, String.valueOf(product)));
            } else if((arg1.getType() == Value4.Type.INT && arg2.getType() == Value4.Type.DOUBLE) || (arg1.getType() == Value4.Type.DOUBLE && arg2.getType() == Value4.Type.INT)) {
                double product = arg1.castDouble() * arg2.castDouble();
                stack.push(new Value4(Value4.Type.DOUBLE, String.valueOf(product)));
            } else {
                // raise an exception as at least one of the arguments is not a double or int
                throw new RuntimeException("[ERROR] Invalid argument for 'add' function, must be a number!");
            }
        } else if("print".equals(functionName)) {
            Value4 value = stack.pop().resolveReference(symbolTable);
            if (value.getType() == Value4.Type.INT) {
                System.out.println(value.castInt());
            } else if (value.getType() == Value4.Type.DOUBLE) {
                System.out.println(value.castDouble());
            } else {
                System.out.println(value.getType() + " " + value);
            }
        } else if("set".equals(functionName)) {
            Value4 variableName = stack.pop();
            Value4 value = stack.pop();
            symbolTable.put(variableName.getValue(), value);
        } else {
            throw new RuntimeException("[ERROR] Unrecognized function " + functionName);
        }
    }

}
