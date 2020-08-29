package com.putoet.utils.assembunny;

import java.util.function.Supplier;

public class Out implements Instruction {
    private final InOperant in;
    private final Supplier<ExecutionContext> supplier;

    public Out(InOperant in, Supplier<ExecutionContext> supplier) {
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
        supplier.get().consumer().accept(in.get());

        return 1;
    }

    @Override
    public String toString() {
        return "out " + in;
    }
}
