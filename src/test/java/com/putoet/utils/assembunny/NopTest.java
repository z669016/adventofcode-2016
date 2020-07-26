package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NopTest {

    @Test
    void execute() {
        final Instruction nop = new Nop();
        assertEquals(1, nop.execute());
    }

    @Test
    void toggle() {
        final Instruction nop = new Nop();
        final Instruction toggle =nop.toggle();

        assertEquals(nop,toggle);
    }
}