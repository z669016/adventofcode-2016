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
        final var register = new Register("ax");
        final var inc = new Inc(new InOperant(register));

        assertEquals("inc ax", inc.toString());

        assertEquals(0, register.get());
        assertEquals(1, inc.execute());
        assertEquals(1, register.get());
        assertEquals(1, inc.execute());
        assertEquals(2, register.get());
    }

    @Test
    void toggle() {
        final var register = new Register("ax");
        final var inc = new Inc(new InOperant(register));
        final var toggle = inc.toggle();

        assertTrue(toggle instanceof Dec);
    }
}