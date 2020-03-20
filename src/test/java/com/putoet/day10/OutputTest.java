package com.putoet.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputTest {

    @Test
    void name() {
         final Output output = new Output(9);
         assertEquals("output-9", output.name());
    }

    @Test
    void contains() {
        final Output output = new Output(1);

        for (int idx = 0; idx < 10; idx++)
            output.accept(new Microchip(idx));

        assertEquals(10, output.size());
        for (int idx = 0; idx < 10; idx++)
            output.contains(new Microchip(idx));
    }
}