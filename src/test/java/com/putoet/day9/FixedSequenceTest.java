package com.putoet.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedSequenceTest {

    public static final String HELLO_WORLD = "hello world";

    @Test
    void text() {
        final var seq = new FixedSequence(HELLO_WORLD);
        assertEquals(HELLO_WORLD, seq.text());
        assertEquals(HELLO_WORLD.length(), seq.length());
    }
}