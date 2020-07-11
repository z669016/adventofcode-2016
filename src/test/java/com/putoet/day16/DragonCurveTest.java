package com.putoet.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DragonCurveTest {

    @Test
    void transform() {
        assertThrows(AssertionError.class, () -> DragonCurve.grow(null));
        assertThrows(AssertionError.class, () -> DragonCurve.grow(""));
        assertThrows(AssertionError.class, () -> DragonCurve.grow("012"));

        assertEquals("100", DragonCurve.grow("1"));
        assertEquals("001", DragonCurve.grow("0"));
        assertEquals("11111000000", DragonCurve.grow("11111"));
        assertEquals("1111000010100101011110000", DragonCurve.grow("111100001010"));
    }

    @Test
    void checksum() {
        assertThrows(AssertionError.class, () -> DragonCurve.checksum(null, 0));
        assertThrows(AssertionError.class, () -> DragonCurve.checksum("1001", 5));
        assertThrows(AssertionError.class, () -> DragonCurve.checksum("10001", 5));

        assertEquals("100", DragonCurve.checksum("110010110100", 12));
    }

    @Test
    void checksumForDiskSpace() {
        assertEquals("01100", DragonCurve.checksumForDiskSpace("10000", 20));
    }
}