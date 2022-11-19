package com.putoet.day11b;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {
    private static final Generator hg = new Generator("hydrogen");
    private static final Microchip hm = new Microchip("hydrogen");
    private static final Generator lg = new Generator("lithium");
    private static final Microchip lm = new Microchip("lithium");

    @Test
    void solve() {
        final Floor[] floors = new Floor[]{
                new Floor(0, Set.of(hm, lm)),
                new Floor(1, Set.of(hg)),
                new Floor(2, Set.of(lg)),
                new Floor(3, Set.of())
        };

        assertEquals(11, Solver.solve(floors));
    }
}