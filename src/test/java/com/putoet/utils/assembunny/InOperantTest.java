package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InOperantTest {

    @Test
    void getConstant() {
        final var value = 3;
        final var in = new InOperant(value);
        assertEquals(value, in.get());
    }

    @Test
    void getRegister() {
        final var value = 3;
        final var register = new Register("a");
        register.accept(value);

        final var in = new InOperant(register);
        assertEquals(value, in.get());
    }

    @Test
    void toStringTest() {
        assertEquals("7", new InOperant(7).toString());
        assertEquals("ax", new InOperant(new Register("ax")).toString());
    }
}