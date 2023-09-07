package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeGridTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new NodeGrid(List.of()));

        assertThrows(IllegalStateException.class, () -> new NodeGrid(List.of(
                new Node(1, 0, 10, 1),
                new Node(2, 0, 10, 1)
        )));

        assertThrows(IllegalStateException.class, () -> new NodeGrid(List.of(
                new Node(0, 1, 10, 1),
                new Node(1, 1, 10, 1)
        )));
    }

    @Test
    void get() {
        final var one = new Node(0, 0, 1, 0);
        final var two = new Node(1, 0, 2, 0);
        final var three = new Node(0, 1, 3, 0);
        final var four = new Node(1, 1, 4, 0);

        final var grid = new NodeGrid(List.of(four, one, two, three));
        assertEquals(one, grid.get(0, 0));
        assertEquals(two, grid.get(1, 0));
        assertEquals(three, grid.get(0, 1));
        assertEquals(four, grid.get(1, 1));
    }
}