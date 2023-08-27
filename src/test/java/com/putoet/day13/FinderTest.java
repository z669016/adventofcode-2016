package com.putoet.day13;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    void find() {
        final var maze = new Maze(10);
        final var finder = new Finder(maze);
        final var result = finder.locate(Point.of(1, 1), Point.of(7, 4));
        assertTrue(result.isPresent());
        assertEquals(11, result.orElseThrow().steps());
    }
}