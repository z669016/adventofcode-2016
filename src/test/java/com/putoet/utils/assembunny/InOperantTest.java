package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InOperantTest {

    @Test
    void getConstant() {
        final int value = 3;

        final InOperant in = new InOperant(value);
        assertEquals(value, in.get());
    }

    @Test
    void getRegister() {
        final int value = 3;

        final Register register = new Register("a");
        register.accept(value);

        final InOperant in = new InOperant(register);
        assertEquals(value, in.get());
    }

    @Test
    void toStringTest() {
        assertEquals("7", new InOperant(7).toString());
        assertEquals("ax", new InOperant(new Register("ax")).toString());
    }
}