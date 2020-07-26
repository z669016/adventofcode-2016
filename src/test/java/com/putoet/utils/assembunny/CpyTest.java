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
        final Register a = new Register("a");
        final Register b = new Register("b");
        final int value = 7;

        final Instruction setA = new Cpy(new InOperant(value), new InOperant(a));
        assertEquals("cpy 7 a", setA.toString());
        final Instruction setB = new Cpy(new InOperant(a), new InOperant(b));
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
        final Register a = new Register("a");
        final Register b = new Register("b");
        final Instruction cpy = new Cpy(new InOperant(a), new InOperant(b));
        final Instruction toggle = cpy.toggle();

        assertEquals("jnz a b", toggle.toString());
    }

    @Test
    void toggle() {
        final int value = 7;
        final Instruction cpy = new Cpy(new InOperant(value), new InOperant(value));
        final Instruction toggle =cpy.toggle();

        assertTrue(toggle instanceof Jnz);
    }

}