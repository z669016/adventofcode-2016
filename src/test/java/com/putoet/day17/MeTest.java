package com.putoet.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeTest {

    @Test
    void whereAmI() {
        final Me me = new Me();

        assertThrows(IllegalArgumentException.class, () -> me.whereAmI(" "));
        assertThrows(IllegalArgumentException.class, () -> me.whereAmI("U"));
        assertThrows(IllegalArgumentException.class, () -> me.whereAmI("L"));
        assertThrows(IllegalArgumentException.class, () -> me.whereAmI("RRRR"));
        assertThrows(IllegalArgumentException.class, () -> me.whereAmI("DDDD"));

        assertEquals(Point.of(0, 0), me.whereAmI(""));
        assertEquals(Point.of(0, 1), me.whereAmI("D"));
        assertEquals(Point.of(1, 0), me.whereAmI("R"));
        assertEquals(Point.of(0, 0), me.whereAmI("DU"));
        assertEquals(Point.of(0, 0), me.whereAmI("RL"));
    }
}