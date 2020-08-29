package com.putoet.utils.assembunny;

import java.util.function.Consumer;

public class ExecutionContext {
    private Register ip;
    private RegisterSet regs;
    private Instruction[] program;
    private Consumer<Integer> consumer;

    public ExecutionContext(Register ip, RegisterSet regs, Instruction[] program, Consumer<Integer> consumer) {
        this.ip = ip;
        this.regs = regs;
        this.program = program;
        this.consumer = consumer;
    }

    public Register ip() { return ip; }
    public RegisterSet regs() { return regs; }
    public Instruction[] program() { return program; }
    public Consumer<Integer> consumer() { return consumer; }
}
