package com.putoet.utils.assembunny;

import java.util.function.Supplier;

public class Tgl implements Instruction {
    private final InOperant in;
    private Supplier<ExecutionContext> supplier;

    public Tgl(InOperant in, Supplier<ExecutionContext> supplier) {
        assert in != null;
        assert supplier != null;

        this.in = in;
        this.supplier = supplier;
    }

    @Override
    public Instruction toggle() {
        return new Inc(in);
    }

    @Override
    public int execute() {
        final ExecutionContext context = supplier.get();
        final Register ip = context.ip();
        final Instruction[] program = context.program();

        final int ptr = ip.get() + in.get();
        if (ptr >= 0 && ptr < program.length)
            program[ptr] = program[ptr].toggle();

        return 1;
    }

    @Override
    public String toString() {
        return "tgl " + in;
    }
}
