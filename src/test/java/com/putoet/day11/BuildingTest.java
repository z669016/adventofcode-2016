package com.putoet.day11;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildingTest {
    private final Generator hg = new Generator("h");
    private final Microchip hm = new Microchip("h");
    private final Generator cg = new Generator("c");
    private final Microchip cm = new Microchip("c");
    private final Generator sg = new Generator("s");
    private final Microchip sm = new Microchip("s");

    @Test
    void canTakeFrom() {
        assertEquals(Set.of(), Building.optionsToTake(Set.of()));
        assertEquals(Set.of(new Pair<>(Set.of(hg), Set.of())), Building.optionsToTake(Set.of(hg)));
        assertEquals(Set.of(new Pair<>(Set.of(hm), Set.of())), Building.optionsToTake(Set.of(hm)));

        assertEquals(Set.of(
                new Pair<>(Set.of(hg), Set.of(hm)),
                new Pair<>(Set.of(hm), Set.of(hg)),
                new Pair<>(Set.of(hm, hg), Set.of())
        ), Building.optionsToTake(Set.of(hg, hm)));

        assertEquals(Set.of(
                new Pair<>(Set.of(hm), Set.of(hg, cg, cm)),
                new Pair<>(Set.of(cm), Set.of(hg, hm, cg)),
                new Pair<>(Set.of(hm, cm), Set.of(hg, cg)),
                new Pair<>(Set.of(hg, cg), Set.of(hm, cm)),
                new Pair<>(Set.of(hg, hm), Set.of(cg, cm)),
                new Pair<>(Set.of(cg, cm), Set.of(hg, hm))
                ), Building.optionsToTake(Set.of(hg, hm, cg, cm)));

        assertEquals(Set.of(
                new Pair<>(Set.of(hm), Set.of(hg, cg, cm, sg, sm)),
                new Pair<>(Set.of(sm), Set.of(hg, hm, cg, cm, sg)),
                new Pair<>(Set.of(cm), Set.of(hg, hm, cg, sg, sm)),
                new Pair<>(Set.of(hm, cm), Set.of(hg, cg, sg, sm)),
                new Pair<>(Set.of(hm, sm), Set.of(hg, cg, cm, sg)),
                new Pair<>(Set.of(sm, cm), Set.of(hg, hm, cg, sg)),
                new Pair<>(Set.of(hg, hm), Set.of(cg, cm, sg, sm)),
                new Pair<>(Set.of(sg, sm), Set.of(hg, hm, cg, cm)),
                new Pair<>(Set.of(cg, cm), Set.of(hg, hm, sg, sm))
        ), Building.optionsToTake(Set.of(hg, hm, cg, cm, sg, sm)));
    }

    @Test
    void solve() {

    }
}