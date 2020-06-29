package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpyTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Cpy(null, new Register("a")));
        assertThrows(AssertionError.class, () -> new Cpy(new InOperant(1), null));
    }

    @Test
    void execute() {
        final Register a = new Register("a");
        final Register b = new Register("b");
        final int value = 7;

        final Instruction setA = new Cpy(new InOperant(value), a);
        assertEquals("cpy 7 a", setA.toString());
        final Instruction setB = new Cpy(new InOperant(a), b);
        assertEquals("cpy a b", setB.toString());

        assertEquals(0, a.get());
        assertEquals(0, b.get());

        assertEquals(1, setA.execute());

        assertEquals(value, a.get());
        assertEquals(0, b.get());

        assertEquals(1, setB.execute());

        assertEquals(value, a.get());
        assertEquals(value, b.get());
    }
}