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
}