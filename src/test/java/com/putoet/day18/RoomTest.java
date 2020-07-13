package com.putoet.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void ofThree() {
        final Room room = Room.of("..^^.", 3);
        assertEquals(6, room.safeTileCount());
        assertEquals( "..^^.\n" +
                ".^^^^\n" +
                "^^..^", room.toString());
    }

    @Test
    void ofTen() {
        final Room room = Room.of(".^^.^.^^^^", 10);
        assertEquals(38, room.safeTileCount());
        assertEquals( ".^^.^.^^^^\n" +
                "^^^...^..^\n" +
                "^.^^.^.^^.\n" +
                "..^^...^^^\n" +
                ".^^^^.^^.^\n" +
                "^^..^.^^..\n" +
                "^^^^..^^^.\n" +
                "^..^^^^.^^\n" +
                ".^^^..^.^^\n" +
                "^^.^^^..^^", room.toString());
    }
}