package com.putoet.utils.assembunny;

public class Jnz implements Instruction {
    private final InOperant offset;
    private final InOperant register;

    public Jnz(InOperant register, InOperant offset) {
        assert register != null;
        assert offset != null;

        this.register = register;
        this.offset = offset;
    }

    @Override
    public Instruction toggle() {
        return new Cpy(register, offset);
    }

    @Override
    public int execute() {
        return register.get() != 0 ? offset.get() : 1;
    }

    @Override
    public String toString() {
        return "jnz " + register + " " + offset;
    }
}
