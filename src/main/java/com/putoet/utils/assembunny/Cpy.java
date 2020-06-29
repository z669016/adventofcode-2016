package com.putoet.utils.assembunny;

public class Cpy implements Instruction {
    private final InOperant in;
    private final Register out;

    public Cpy(InOperant in, Register out) {
        assert in != null;
        assert out != null;

        this.in = in;
        this.out = out;
    }

    @Override
    public int execute() {
        out.accept(in.get());
        return 1;
    }

    @Override
    public String toString() {
        return "cpy " + in.toString() + " " + out.name();
    }
}
