package com.putoet.utils.assembunny;

import java.util.*;
import java.util.stream.Collectors;

public class RegisterSet {
    private final Map<String, Register> map = new HashMap<>();

    public static RegisterSet of(Register ... registers) {
        final var set = new RegisterSet();
        Arrays.stream(registers)
                .forEach(register -> set.map.put(register.name(), register));
        return set;
    }

    public Optional<Register> get(String name) {
        return Optional.ofNullable(map.get(name));
    }

    @Override
    public String toString() {
        return map.values().stream()
                .sorted(Comparator.comparing(Register::name))
                .map(register -> register.name() + ":" + register.get())
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
