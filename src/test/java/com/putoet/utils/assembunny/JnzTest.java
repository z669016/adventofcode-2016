package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JnzTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Jnz(null, new InOperant(1)));
        assertThrows(AssertionError.class, () -> new Jnz(new InOperant(new Register("a")), null));
    }

    @Test
    void execute() {
        final var register = new Register("a");
        final var offset = 13;
        final var jnz = new Jnz(new InOperant(register), new InOperant(offset));

        assertEquals("jnz a 13", jnz.toString());

        assertEquals(1, jnz.execute());
        register.accept(7);
        assertEquals(offset, jnz.execute());
    }

    @Test
    void toggle() {
        final var register = new Register("a");
        final var offset = 13;
        final var jnz = new Jnz(new InOperant(register), new InOperant(offset));
        final var toggle = jnz.toggle();

        assertTrue(toggle instanceof Cpy);
    }
}