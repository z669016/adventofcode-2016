package com.putoet.day15;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SculptureTest {
    @Test
    void create() {
        assertThrows(AssertionError.class,() -> new Sculpture(null));
        assertThrows(AssertionError.class,() -> new Sculpture(List.of()));
    }

    @Test
    void isOpenOn() {
        Disk.reset();
        final Sculpture sculpture = new Sculpture(List.of(
                Disk.of(5,4),
                Disk.of(2, 1)
        ));

        assertFalse(sculpture.isOpenOn(0));
        assertTrue(sculpture.isOpenOn(5));
    }
}