package com.putoet.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecompressorTest {

    @Test
    void decompress() {
        assertEquals("ADVENT", Decompressor.decompress("ADVENT"));
        assertEquals("ABBBBBC", Decompressor.decompress("A(1x5)BC"));
        assertEquals("XYZXYZXYZ", Decompressor.decompress("(3x3)XYZ"));
        assertEquals("ABCBCDEFEFG", Decompressor.decompress("A(2x2)BCD(2x2)EFG"));
        assertEquals("(1x3)A", Decompressor.decompress("(6x1)(1x3)A"));
        assertEquals("X(3x3)ABC(3x3)ABCY", Decompressor.decompress("X(8x2)(3x3)ABCY"));
    }

    @Test
    void decompressedLength() {
        assertEquals(6, Decompressor.decompressedLength("ADVENT"));
        assertEquals(7, Decompressor.decompressedLength("A(1x5)BC"));
        assertEquals(9, Decompressor.decompressedLength("(3x3)XYZ"));
        assertEquals(11, Decompressor.decompressedLength("A(2x2)BCD(2x2)EFG"));
        assertEquals(6, Decompressor.decompressedLength("(6x1)(1x3)A"));
        assertEquals(18, Decompressor.decompressedLength("X(8x2)(3x3)ABCY"));
    }

    @Test
    void decompressedLengthV2() {
        assertEquals(9, Decompressor.decompressedLengthV2("(3x3)XYZ"));
        assertEquals(20, Decompressor.decompressedLengthV2("X(8x2)(3x3)ABCY"));
        assertEquals(241920, Decompressor.decompressedLengthV2("(27x12)(20x12)(13x14)(7x10)(1x12)A"));
        assertEquals(445, Decompressor.decompressedLengthV2("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"));
    }
}