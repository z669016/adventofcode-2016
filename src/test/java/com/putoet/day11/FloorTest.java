package com.putoet.day11;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void isInvalid() {
        assertFalse(new Floor(0, List.of(
                Pair.with(2, 0),
                Pair.with(0, 1),
                Pair.with(0, 1),
                Pair.with(0, 0)
        )).isInvalid());

        assertTrue(new Floor(0, List.of(
                Pair.with(2, 1),
                Pair.with(0, 0),
                Pair.with(0, 1),
                Pair.with(0, 0)
        )).isInvalid());
    }

    @Test
    void done() {
        assertFalse(new Floor(0, List.of(
                Pair.with(2, 0),
                Pair.with(0, 1),
                Pair.with(0, 1),
                Pair.with(0, 0)
        )).done());
        assertTrue(new Floor(3, List.of(
                Pair.with(0, 0),
                Pair.with(0, 0),
                Pair.with(0, 0),
                Pair.with(4, 4)
        )).done());
    }

    @Test
    void next() {
        final Floor floor = new Floor(0, List.of(
                Pair.with(2, 0),
                Pair.with(0, 1),
                Pair.with(0, 1),
                Pair.with(0, 0)
        ));

        assertEquals(1, floor.next().size());
    }

    @Test
    void belowIsEmpty() {
        assertTrue(new Floor(0, List.of(
                Pair.with(2, 0),
                Pair.with(0, 1),
                Pair.with(0, 1),
                Pair.with(0, 0)
        )).belowIsEmpty());
        assertFalse(new Floor(1, List.of(
                Pair.with(2, 0),
                Pair.with(0, 1),
                Pair.with(0, 1),
                Pair.with(0, 0)
        )).belowIsEmpty());
        assertTrue(new Floor(1, List.of(
                Pair.with(0, 0),
                Pair.with(1, 1),
                Pair.with(1, 1),
                Pair.with(0, 0)
        )).belowIsEmpty());
    }
}