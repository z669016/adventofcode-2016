package com.putoet.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FixedNonNegativeGridTest {
    @Test
    void createInitialized() {
        final FixedGrid<String> grid = new FixedNonNegativeGrid<>(List.of(
                List.of("A", "B", "C", "1"),
                List.of("D", "E", "F", "2"),
                List.of("G", "H","I", "3"))
        );

        assertEquals(3, grid.height());
        assertEquals(4, grid.width());
        assertEquals("D", grid.get(0,1));
        assertEquals("3", grid.get(3, 2));
    }

    @Test
    void createUninitialized() {
        final FixedGrid<Integer> grid = new FixedNonNegativeGrid<>(4, 3, -1);
        assertEquals(4, grid.height());
        assertEquals(3, grid.width());

        assertEquals(-1, grid.get(1, 1));
    }

    @Test
    void createInvalid() {
        // All rows must have the same size
        assertThrows(AssertionError.class, () -> new FixedNonNegativeGrid<>(List.of(
                List.of("A", "B", "C", "1"),
                List.of("D", "E", "F"),
                List.of("G", "H","I", "3"))
        ));

        assertThrows(AssertionError.class, () -> new FixedNonNegativeGrid<>(0, 2, ""));
        assertThrows(AssertionError.class, () -> new FixedNonNegativeGrid<>(2, 0, ""));
        assertThrows(AssertionError.class, () -> new FixedNonNegativeGrid<String>(2, 2, null));
    }
}