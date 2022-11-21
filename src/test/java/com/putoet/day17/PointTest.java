package com.putoet.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> Point.of(4, 0));
        assertThrows(AssertionError.class, () -> Point.of(0, 4));
    }

    @Test
    void right() {
        assertTrue(Point.of(2, 0).right().isPresent());
        assertFalse(Point.of(3, 0).right().isPresent());
    }

    @Test
    void down() {
        assertTrue(Point.of(0, 2).down().isPresent());
        assertFalse(Point.of(0, 3).down().isPresent());
    }
}