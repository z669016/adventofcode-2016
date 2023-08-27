package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpyTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Cpy(null, new InOperant(new Register("a"))));
        assertThrows(AssertionError.class, () -> new Cpy(new InOperant(1), null));
    }

    @Test
    void execute() {
        final var a = new Register("a");
        final var b = new Register("b");
        final var value = 7;

        final var setA = new Cpy(new InOperant(value), new InOperant(a));
        assertEquals("cpy 7 a", setA.toString());
        final var setB = new Cpy(new InOperant(a), new InOperant(b));
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

    @Test
    void executeInvalid() {
        final var a = new Register("a");
        final var b = new Register("b");
        final var cpy = new Cpy(new InOperant(a), new InOperant(b));
        final var toggle = cpy.toggle();

        assertEquals("jnz a b", toggle.toString());
    }

    @Test
    void toggle() {
        final var value = 7;
        final var cpy = new Cpy(new InOperant(value), new InOperant(value));
        final var toggle = cpy.toggle();

        assertTrue(toggle instanceof Jnz);
    }
}