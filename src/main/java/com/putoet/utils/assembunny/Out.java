package com.putoet.utils.assembunny;

public class Inc implements Instruction {
    private final InOperant in;

    public Inc(InOperant in) {
        assert in != null;

        this.in = in;
    }

    @Override
    public Instruction toggle() {
        return new Dec(in);
    }

    @Override
    public int execute() {
        if (in.isRegister())
            in.register().accept(in.register().get() + 1);

        return 1;
    }

    @Override
    public String toString() {
        return "inc " + in;
    }
}
