package com.putoet.utils.assembunny;

public class Nop implements Instruction {
    @Override
    public int execute() {
        return 1;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
