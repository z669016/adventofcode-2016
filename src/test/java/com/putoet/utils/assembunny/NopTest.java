package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NopTest {

    @Test
    void execute() {
        final var nop = new Nop();
        assertEquals(1, nop.execute());
    }

    @Test
    void toggle() {
        final var nop = new Nop();
        final var toggle = nop.toggle();

        assertEquals(nop, toggle);
    }
}