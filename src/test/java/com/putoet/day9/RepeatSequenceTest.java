package com.putoet.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatSequenceTest {

    @Test
    void text() {
        assertThrows(IllegalArgumentException.class, () -> new RepeatSequence("bla"));

        assertEquals("XYZXYZXYZ", new RepeatSequence("(3x3)XYZ").text());
        assertEquals(9, new RepeatSequence("(3x3)XYZ").length());
        assertEquals("ABCABCABCABCABCABC", new RepeatSequence("(8x2)(3x3)ABC").text());
        assertEquals(18, new RepeatSequence("(8x2)(3x3)ABC").length());
    }
}