package com.putoet.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    @Test
    void possibleTriangle() {
        assertFalse(new Figure(5, 10, 25).possibleTriangle());
        assertFalse(new Figure(10, 5, 25).possibleTriangle());
        assertFalse(new Figure(10, 25, 5).possibleTriangle());

        assertTrue(new Figure(2, 3, 4).possibleTriangle());
    }
}