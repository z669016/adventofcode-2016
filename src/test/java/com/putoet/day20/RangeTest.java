package com.putoet.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> Range.of(-1, 1));
        assertThrows(AssertionError.class, () -> Range.of(1, 0));
    }

    @Test
    void of() {
        assertThrows(IllegalArgumentException.class, () -> Range.of("1234"));

        assertEquals(Range.of(12, 25), Range.of("12-25"));
    }

    @Test
    void overlaps() {
        assertTrue(Range.of(1, 3).overlaps(Range.of(2,5)));
        assertTrue(Range.of(2, 5).overlaps(Range.of(1,3)));

        assertTrue(Range.of(1, 5).overlaps(Range.of(2,3)));
        assertTrue(Range.of(2, 3).overlaps(Range.of(1,5)));

        assertFalse(Range.of(1, 2).overlaps(Range.of(4,6)));
        assertFalse(Range.of(4, 6).overlaps(Range.of(1,2)));
    }

    @Test
    void compareTo() {
        assertEquals(0, Range.of(1, 2).compareTo(Range.of(1, 2)));
        assertEquals(-1, Range.of(1, 2).compareTo(Range.of(1, 3)));
        assertEquals(1, Range.of(1, 3).compareTo(Range.of(1, 2)));
        assertEquals(-1, Range.of(1, 2).compareTo(Range.of(2, 3)));
        assertEquals(1, Range.of(1, 2).compareTo(Range.of(0, 3)));
    }
}