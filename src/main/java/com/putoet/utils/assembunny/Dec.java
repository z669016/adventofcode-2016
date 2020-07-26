package com.putoet.utils.assembunny;

public class Dec implements Instruction {
    private final InOperant in;

    public Dec(InOperant in) {
        assert in != null;

        this.in = in;
    }

    @Override
    public Instruction toggle() {
        return new Inc(in);
    }

    @Override
    public int execute() {
        if (in.isRegister())
            in.register().accept(in.register().get() - 1);
        return 1;
    }

    @Override
    public String toString() {
        return "dec " + in;
    }
}
