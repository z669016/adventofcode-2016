package com.putoet.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {
    @Test
    void solve() {
        var floor = new Floor(0, List.of(
                new FloorState(2, 0),
                new FloorState(0, 1),
                new FloorState(0, 1),
                new FloorState(0, 0)
        ));
        var state = Day11.solve(floor);
        assertNotNull(state);
        assertEquals(11, state.steps());
    }
}