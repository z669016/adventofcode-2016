package com.putoet.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void of() {
        assertThrows(IllegalArgumentException.class, () -> Direction.of('1'));

        assertEquals(Direction.UP, Direction.of('U'));
        assertEquals(Direction.RIGHT, Direction.of('R'));
        assertEquals(Direction.DOWN, Direction.of('D'));
        assertEquals(Direction.LEFT, Direction.of('L'));
    }
}