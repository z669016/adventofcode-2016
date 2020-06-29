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
        final Register register = new Register("a");
        final int offset = 13;
        final Instruction jnz = new Jnz(new InOperant(register), new InOperant(offset));

        assertEquals("jnz a 13", jnz.toString());

        assertEquals(1, jnz.execute());
        register.accept(7);
        assertEquals(offset, jnz.execute());
    }
}