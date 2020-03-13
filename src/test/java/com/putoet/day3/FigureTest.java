package com.putoet.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    @Test
    void possibleTriangle() {
        assertFalse(Figure.from(5, 10, 25).possibleTriangle());
        assertFalse(Figure.from(10, 5, 25).possibleTriangle());
        assertFalse(Figure.from(10, 25, 5).possibleTriangle());

        assertTrue(Figure.from(2, 3, 4).possibleTriangle());
    }
}