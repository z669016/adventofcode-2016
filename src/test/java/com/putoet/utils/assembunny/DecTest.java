package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Inc(null));
    }

    @Test
    void execute() {
        final Register register = new Register("ax");
        final Instruction dec = new Dec(new InOperant(register));

        assertEquals("dec ax", dec.toString());

        assertEquals(0, register.get());
        assertEquals(1, dec.execute());
        assertEquals(-1, register.get());
        assertEquals(1, dec.execute());
        assertEquals(-2, register.get());
    }

    @Test
    void toggle() {
        final Register register = new Register("ax");
        final Instruction dec = new Dec(new InOperant(register));
        final Instruction toggle = dec.toggle();

        assertTrue(toggle instanceof Inc);
    }
}