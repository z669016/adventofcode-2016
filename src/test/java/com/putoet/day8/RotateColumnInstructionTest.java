package com.putoet.day8;

import com.putoet.utils.FixedGrid;
import com.putoet.utils.FixedNonNegativeGrid;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotateColumnInstructionTest {

    @Test
    void accept() {
        final TinyCodeDisplayingScreen display = new TinyCodeDisplayingScreen();
        final FixedGrid<Integer> grid = new FixedNonNegativeGrid<>(List.of(
                List.of(1, 1, 1, 0, 0, 0, 0),
                List.of(1, 1, 1, 0, 0, 0, 0),
                List.of(0, 0, 0, 0, 0, 0, 0)
        ));

        new RotateColumnInstruction(2, 5).accept(grid);
        display.display(grid);

        assertEquals(1, grid.get(0,0));
        assertEquals(1, grid.get(1,0));
        assertEquals(1, grid.get(2,0));
        assertEquals(0, grid.get(3,0));
        assertEquals(0, grid.get(4,0));
        assertEquals(0, grid.get(5,0));
        assertEquals(0, grid.get(6,0));
        assertEquals(1, grid.get(0,1));
        assertEquals(1, grid.get(1,1));
        assertEquals(0, grid.get(2,1));
        assertEquals(0, grid.get(3,1));
        assertEquals(0, grid.get(4,1));
        assertEquals(0, grid.get(5,1));
        assertEquals(0, grid.get(6,1));
        assertEquals(0, grid.get(0,2));
        assertEquals(0, grid.get(1,2));
        assertEquals(1, grid.get(2,2));
        assertEquals(0, grid.get(3,2));
        assertEquals(0, grid.get(4,2));
        assertEquals(0, grid.get(5,2));
        assertEquals(0, grid.get(6,2));
    }
}