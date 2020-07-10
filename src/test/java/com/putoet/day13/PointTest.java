package com.putoet.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void of() {
        final Point point = Point.of(3, 7);
        assertEquals(3, point.x());
        assertEquals(7, point.y());
    }

    @Test
    void left() {
        assertFalse(Point.of(0, 1).left().isPresent());
        assertEquals(Point.of(0, 0), Point.of(1, 0).left().get());
    }

    @Test
    void right() {
        assertEquals(Point.of(1, 0), Point.of(0, 0).right().get());
    }

    @Test
    void up() {
        assertFalse(Point.of(0, 0).up().isPresent());
        assertEquals(Point.of(0, 0), Point.of(0, 1).up().get());
    }

    @Test
    void down() {
        assertEquals(Point.of(0, 1), Point.of(0, 0).down().get());
    }
}