package com.putoet.day1;

import org.junit.jupiter.api.Test;

import static com.putoet.day1.Location.Direction.EAST;
import static com.putoet.day1.Location.Direction.WEST;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void facing() {
        final var location = new Location();
        final var direction = location.facing();
        assertEquals(Location.Direction.NORTH, direction);

        assertEquals(WEST, direction.turn(Location.Turn.RIGHT));
        assertEquals(EAST, direction.turn(Location.Turn.LEFT));
    }
}