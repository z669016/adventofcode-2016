package com.putoet.utils.assembunny;

public class Inc implements Instruction {
    private final Register register;

    public Inc(Register register) {
        assert register != null;

        this.register = register;
    }

    @Override
    public int execute() {
        register.accept(register.get() + 1);
        return 1;
    }

    @Override
    public String toString() {
        return "inc " + register.name();
    }
}
