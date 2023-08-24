package com.putoet.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceBuilderTest {

    @Test
    void from() {
        var sequence = SequenceBuilder.from("(3x3)XYZ");
        assertEquals("XYZXYZXYZ", sequence.text());
        assertEquals(9, sequence.length());

        sequence = SequenceBuilder.from("X(8x2)(3x3)ABCY");
        assertEquals("XABCABCABCABCABCABCY", sequence.text());
        assertEquals(20, sequence.length());

        sequence = SequenceBuilder.from("(27x12)(20x12)(13x14)(7x10)(1x12)A");
        assertEquals(241920, sequence.length());

        final var line = "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN";
        sequence = SequenceBuilder.from(line);
        assertEquals(line, sequence.toString());
        assertEquals(445, sequence.length());
    }
}