package com.putoet.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceValidatorTest {

    @Test
    void triplet() {
        assertFalse(SequenceValidator.triplet("").isPresent());
        assertFalse(SequenceValidator.triplet("1").isPresent());
        assertFalse(SequenceValidator.triplet("11").isPresent());

        final var triplet = SequenceValidator.triplet("111");
        assertTrue(triplet.isPresent());
        assertEquals("1", triplet.get());

        assertTrue(SequenceValidator.triplet("1111").isPresent());
        assertTrue(SequenceValidator.triplet("01111").isPresent());
        assertTrue(SequenceValidator.triplet("01110").isPresent());
        assertTrue(SequenceValidator.triplet("011").isEmpty());
        assertTrue(SequenceValidator.triplet("0111").isPresent());
    }

    @Test
    void fivelet() {
        assertThrows(AssertionError.class, () -> SequenceValidator.fivelet("", ""));
        assertThrows(AssertionError.class, () -> SequenceValidator.fivelet("11", ""));

        assertFalse(SequenceValidator.fivelet("1", ""));
        assertFalse(SequenceValidator.fivelet("1", "1"));
        assertFalse(SequenceValidator.fivelet("1", "11"));
        assertFalse(SequenceValidator.fivelet("1", "111"));
        assertFalse(SequenceValidator.fivelet("1", "1111"));

        assertTrue(SequenceValidator.fivelet("1", "11111"));
    }
}