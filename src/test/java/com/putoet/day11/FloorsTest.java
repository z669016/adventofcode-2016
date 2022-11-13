package com.putoet.day11;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FloorsTest {
    private final Generator hg = new Generator("H");
    private final Microchip hm = new Microchip("H");
    private final Generator cg = new Generator("C");
    private final Microchip cm = new Microchip("C");
    private final Generator pg = new Generator("P");
    private final Microchip pm = new Microchip("P");

    @Test
    void testEquals() {
        final Floor[] first = new Floor[] {
                new Floor(0, Set.of(pg)),
                new Floor(1, Set.of(hm, hg)),
                new Floor(2, Set.of(cm, cg)),
                new Floor(3, Set.of(pm))
        };
        final Floor[] second = new Floor[] {
                new Floor(0, Set.of(pg)),
                new Floor(1, Set.of(cm, cg)),
                new Floor(2, Set.of(hm, hg)),
                new Floor(3, Set.of(pm))
        };

        assertTrue(Floors.equals(first, second));
    }
}