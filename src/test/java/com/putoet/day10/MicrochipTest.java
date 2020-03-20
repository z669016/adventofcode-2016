package com.putoet.day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MicrochipTest {
    private Microchip chip;

    @BeforeEach
    void setup() {
        chip = new Microchip(13);
    }

    @Test
    void value() {
        assertEquals(13, chip.value());
    }

    @Test
    void testToString() {
        assertEquals("value-13", chip.toString());
    }

    @Test
    void testEquals() {
        assertEquals(chip, new Microchip(13));
        assertNotEquals(chip, new Microchip(11));
    }

    @Test
    void compareTo() {
        assertTrue(chip.compareTo(new Microchip(17)) < 0);
        assertTrue(chip.compareTo(new Microchip(13)) == 0);
        assertTrue(chip.compareTo(new Microchip(11)) > 0);
    }
}