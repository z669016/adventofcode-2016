package com.putoet.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiskTest {

    @Test
    void position() {
        final Disk one = Disk.of(5,4);
        final Disk two = Disk.of(2, 1);

        assertEquals(0, one.position().apply(0));
        assertEquals(1, one.position().apply(1));
        assertEquals(2, one.position().apply(2));
        assertEquals(3, one.position().apply(3));
        assertEquals(4, one.position().apply(4));
        assertEquals(0, one.position().apply(5));

        assertEquals(1, two.position().apply(0));
        assertEquals(0, two.position().apply(1));
        assertEquals(1, two.position().apply(2));
        assertEquals(0, two.position().apply(3));
        assertEquals(1, two.position().apply(4));
    }
}