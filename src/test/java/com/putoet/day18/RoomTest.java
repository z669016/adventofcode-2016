package com.putoet.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    @Test
    void of() {
        assertThrows(AssertionError.class, () -> Room.of(null, 10));
        assertThrows(AssertionError.class, () -> Room.of("", 10));
        assertThrows(AssertionError.class, () -> Room.of(".1.", 10));
        assertThrows(AssertionError.class, () -> Room.of("...", 0));

    }

    @Test
    void ofThree() {
        final Room room = Room.of("..^^.", 3);
        assertEquals(6, room.safeTileCount());
        assertEquals("""
                ..^^.
                .^^^^
                ^^..^""", room.toString());
    }

    @Test
    void ofTen() {
        final Room room = Room.of(".^^.^.^^^^", 10);
        assertEquals(38, room.safeTileCount());
        assertEquals("""
                .^^.^.^^^^
                ^^^...^..^
                ^.^^.^.^^.
                ..^^...^^^
                .^^^^.^^.^
                ^^..^.^^..
                ^^^^..^^^.
                ^..^^^^.^^
                .^^^..^.^^
                ^^.^^^..^^""", room.toString());
    }
}