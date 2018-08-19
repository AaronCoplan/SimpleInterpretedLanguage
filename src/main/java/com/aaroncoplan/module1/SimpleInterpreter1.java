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
        System.out.println(code);
    }
}