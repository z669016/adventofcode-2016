package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Inc(null));
    }

    @Test
    void execute() {
        final Register register = new Register("ax");
        final Instruction inc = new Inc(register);

        assertEquals("inc ax", inc.toString());

        assertEquals(0, register.get());
        assertEquals(1, inc.execute());
        assertEquals(1, register.get());
        assertEquals(1, inc.execute());
        assertEquals(2, register.get());
    }
}