package com.putoet.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.putoet.day1.Location.Direction.EAST;
import static com.putoet.day1.Location.Direction.WEST;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void facing() {
        final Location location = new Location();
        final Location.Direction direction = location.facing();
        assertEquals(Location.Direction.NORTH, direction);

        assertEquals(WEST, direction.turn(Location.Turn.RIGHT));
        assertEquals(EAST, direction.turn(Location.Turn.LEFT));
    }

    @Test
    void move() {
        final Location location = new Location();
        for (int idx = 0; idx < 4; idx++) {
            location.move(Location.Turn.RIGHT, idx + 1);
        }
        for (int idx = 0; idx < 4; idx++) {
            location.move(Location.Turn.LEFT, idx + 1);
        }

        assertEquals(List.of(8, 4, 4, 4), location.moved());
    }

    @Test
    void distance() {
        Location location = new Location();
        location.move(List.of("R2", "L3"));
        assertEquals(5, location.distance());

        location = new Location();
        location.move(List.of("R2", "R2", "R2"));
        assertEquals(2, location.distance());

        location = new Location();
        location.move(List.of("R5", "L5", "R5", "R3"));
        assertEquals(12, location.distance());

        location = new Location();
        location.move(List.of("R8", "R4", "R4", "R8"));
        assertEquals(4, location.doubles().get(0).distance());
    }
}