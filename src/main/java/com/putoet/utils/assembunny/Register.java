package com.putoet.utils.assembunny;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Register implements Consumer<Integer>, Supplier<Integer> {
    private final String name;
    private int value;

    public Register(String name) {
        assert isValidName(name);
        this.name = name;
    }

    public String name() {
        return name;
    }

    private static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.chars().allMatch(Character::isLetter);
    }

    @Override
    public void accept(Integer value) {
        assert value != null;

        this.value = value;
    }

    @Override
    public Integer get() {
        return value;
    }

    @Override
    public String toString() {
        return "Reg {name:" + name + ", value:" + value + "}";
    }
}
