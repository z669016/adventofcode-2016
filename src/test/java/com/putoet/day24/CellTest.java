package com.putoet.day24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void of() {
        assertThrows(IllegalArgumentException.class, () -> Cell.of('*'));

        assertThrows(IllegalStateException.class, () -> Cell.of('#').number());
        assertThrows(IllegalStateException.class, () -> Cell.of('.').number());

        assertEquals(Cell.Type.WALL, Cell.of('#').type());
        assertEquals(Cell.Type.OPEN, Cell.of('.').type());

        final Cell cell = Cell.of('3');
        assertEquals(Cell.Type.GATE, cell.type());
        assertEquals(3, cell.number());
    }
}