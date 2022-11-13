package com.putoet.day11;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloorTest {
    private final Generator hg = new Generator("H");
    private final Generator cg = new Generator("C");
    private final Microchip hm = new Microchip("H");
    private final Microchip cm = new Microchip("C");

    @Test
    void isValid() {
        assertTrue(Floor.isValid(Set.of(hg, hm, cg, cm)));
        assertTrue(Floor.isValid(Set.of(hm, cm)));
        assertTrue(Floor.isValid(Set.of(hg, hm, cg)));
        assertTrue(Floor.isValid(Set.of(hg, cg)));
        assertFalse(Floor.isValid(Set.of(hg, hm, cm)));
        assertFalse(Floor.isValid(Set.of(hm, cg)));
    }
}