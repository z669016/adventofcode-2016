package com.putoet.day11;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloorTest {
    private Generator hg = new Generator("H");
    private Generator cg = new Generator("C");
    private Microchip hm = new Microchip("H");
    private Microchip cm = new Microchip("C");

    @Test
    void isValid() {
        assertTrue(new Floor(0, Set.of(hg, hm, cg, cm)).isValid());
        assertTrue(new Floor(0, Set.of(hm, cm)).isValid());
        assertTrue(new Floor(0, Set.of(hg, hm, cg)).isValid());
        assertTrue(new Floor(0, Set.of(hg, cg)).isValid());
        assertFalse(new Floor(0, Set.of(hg, hm, cm)).isValid());
        assertFalse(new Floor(0, Set.of(hm, cg)).isValid());
    }
}