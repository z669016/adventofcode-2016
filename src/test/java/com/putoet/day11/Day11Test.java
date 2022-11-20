package com.putoet.day11;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {
    @Test
    void solve() {
        var floor = new Floor(0, List.of(
                Pair.with(2, 0),
                Pair.with(0, 1),
                Pair.with(0, 1),
                Pair.with(0, 0)
        ));
        var state = Day11.solve(floor);
        assertNotNull(state);
        assertEquals(11, state.steps());
    }
}