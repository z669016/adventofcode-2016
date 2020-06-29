package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Register(null));
        assertThrows(AssertionError.class, () -> new Register(""));
        assertThrows(AssertionError.class, () -> new Register("1"));
    }

    @Test
    void name() {
        final String name = "ax";
        assertEquals(name, new Register(name).name());
    }

    @Test
    void get() {
        final int value = 11;
        final Register register = new Register("ax");
        register.accept(value);
        assertEquals(value, register.get());
    }
}