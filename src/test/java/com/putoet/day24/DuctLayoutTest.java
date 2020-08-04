package com.putoet.day24;

import com.putoet.maze.Maze;
import com.putoet.search.GenericSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.putoet.day24.Cell.*;
import static com.putoet.search.GenericSearch.bfs;
import static org.junit.jupiter.api.Assertions.*;

class DuctLayoutTest {
    private DuctLayout layout;

    @BeforeEach
    void setup() {
        final List<String> lines = List.of(
                "###########",
                "#0.1.....2#",
                "#.#######.#",
                "#4.......3#",
                "###########"
        );

        layout = new DuctLayout(lines);
    }

    @Test
    void draw() {
        System.out.println(layout.toString());
    }

    @Test
    void gates() {
        final List<Cell> gates = layout.gates();
        assertEquals(5, gates.size());
        assertEquals(List.of(GATE0, GATE1, GATE2, GATE3, GATE4), gates);
    }

    @Test
    void bsf() {
        final Optional<GenericSearch.Node<Maze.Location>> result =
                bfs(Maze.Location.of(1, 1), ml -> layout.cell(ml) == GATE4, layout::successors);
        assertTrue(result.isPresent());
        assertEquals(Maze.Location.of(3, 1), result.get().state);

        System.out.println(result.get().path());
    }

    @Test
    void combinations() {
        final List<List<Cell>> combinations = layout.gateCombinations();
        final Map<String, Map<String,Integer>> distances = layout.distances(combinations);

        // distances.forEach((uk, uv) -> uv.forEach((vk, vv) -> System.out.println(uk + " -> " + vk + " = " + vv)));

        final OptionalInt min = layout.shortestPathFrom(GATE0, distances);

        assertTrue(min.isPresent());
        assertEquals(14, min.getAsInt());
    }
}