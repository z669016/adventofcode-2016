package com.putoet.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void isInvalid() {
        assertFalse(new Floor(0, List.of(
                new FloorState(2, 0),
                new FloorState(0, 1),
                new FloorState(0, 1),
                new FloorState(0, 0)
        )).isInvalid());

        assertTrue(new Floor(0, List.of(
                new FloorState(2, 1),
                new FloorState(0, 0),
                new FloorState(0, 1),
                new FloorState(0, 0)
        )).isInvalid());
    }

    @Test
    void done() {
        assertFalse(new Floor(0, List.of(
                new FloorState(2, 0),
                new FloorState(0, 1),
                new FloorState(0, 1),
                new FloorState(0, 0)
        )).done());
        assertTrue(new Floor(3, List.of(
                new FloorState(0, 0),
                new FloorState(0, 0),
                new FloorState(0, 0),
                new FloorState(4, 4)
        )).done());
    }

    @Test
    void next() {
        final Floor floor = new Floor(0, List.of(
                new FloorState(2, 0),
                new FloorState(0, 1),
                new FloorState(0, 1),
                new FloorState(0, 0)
        ));

        assertEquals(1, floor.next().size());
    }

    @Test
    void belowIsEmpty() {
        assertTrue(new Floor(0, List.of(
                new FloorState(2, 0),
                new FloorState(0, 1),
                new FloorState(0, 1),
                new FloorState(0, 0)
        )).belowIsEmpty());
        assertFalse(new Floor(1, List.of(
                new FloorState(2, 0),
                new FloorState(0, 1),
                new FloorState(0, 1),
                new FloorState(0, 0)
        )).belowIsEmpty());
        assertTrue(new Floor(1, List.of(
                new FloorState(0, 0),
                new FloorState(1, 1),
                new FloorState(1, 1),
                new FloorState(0, 0)
        )).belowIsEmpty());
    }
}