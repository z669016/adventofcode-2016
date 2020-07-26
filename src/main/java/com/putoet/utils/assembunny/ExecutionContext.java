package com.putoet.utils.assembunny;

public class ExecutionContext {
    private Register ip;
    private RegisterSet regs;
    private Instruction[] program;

    public ExecutionContext(Register ip, RegisterSet regs, Instruction[] program) {
        this.ip = ip;
        this.regs = regs;
        this.program = program;
    }

    public Register ip() { return ip; }
    public RegisterSet regs() { return regs; }
    public Instruction[] program() { return program; }
}
