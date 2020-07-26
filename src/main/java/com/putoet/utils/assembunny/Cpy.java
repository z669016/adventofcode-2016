package com.putoet.utils.assembunny;

public class Cpy implements Instruction {
    private final InOperant in;
    private final InOperant out;

    public Cpy(InOperant in, InOperant out) {
        assert in != null;
        assert out != null;

        this.in = in;
        this.out = out;
    }

    @Override
    public Instruction toggle() {
        return new Jnz(in, out);
    }

    @Override
    public int execute() {
        if (out.isRegister())
            out.register().accept(in.get());
        return 1;
    }

    @Override
    public String toString() {
        return "cpy " + in.toString() + " " + (out.isRegister() ? out.register().name() : out.get());
    }
}
