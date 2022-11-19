package com.putoet.day11b;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FloorsTest {
    private final Generator cg = new Generator("C");
    private final Microchip cm = new Microchip("C");
    private final Generator pg = new Generator("P");
    private final Microchip pm = new Microchip("P");
    private final Generator sg = new Generator("S");
    private final Microchip sm = new Microchip("S");
    private final Generator tg = new Generator("T");
    private final Microchip tm = new Microchip("T");
    private final Generator rg = new Generator("R");
    private final Microchip rm = new Microchip("R");

    @Test
    void testEquals() {
        final Floor[] first = new Floor[] {
                new Floor(0, Set.of(pg, pm)),
                new Floor(1, Set.of(sm, sg,rm,rg,tg,cm,cg)),
                new Floor(2, Set.of(tm)),
                new Floor(3, Set.of())
        };
        final Floor[] second = new Floor[] {
                new Floor(0, Set.of(sg, sm)),
                new Floor(1, Set.of(pm, pg,rm,rg,tg,cm,cg)),
                new Floor(2, Set.of(tm)),
                new Floor(3, Set.of())
        };

        assertTrue(new Floors(first).equals(new Floors(second)));
    }
}