package com.putoet.day24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        layout = DuctLayout.of(lines);
    }

    @Test
    void draw() {
        layout.draw();
    }

    @Test
    void is() {
        assertTrue(layout.isWall(1, 0));
        assertTrue(layout.isOpen(2, 1));
        assertTrue(layout.isGate(3, 1));
    }

    @Test
    void gates() {
        final List<Cell> gates = layout.gates();
        assertEquals(5, gates.size());
        assertEquals(Set.of(0, 1, 2, 3, 4), gates.stream().map(Cell::number).collect(Collectors.toSet()));
    }
}