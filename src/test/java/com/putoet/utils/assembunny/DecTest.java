package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DecTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Inc(null));
    }

    @Test
    void execute() {
        final Register register = new Register("ax");
        final Instruction dec = new Dec(register);

        assertEquals("dec ax", dec.toString());

        assertEquals(0, register.get());
        assertEquals(1, dec.execute());
        assertEquals(-1, register.get());
        assertEquals(1, dec.execute());
        assertEquals(-2, register.get());
    }
}