package com.putoet.day18;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day18 {

    public static final String PUZZLE_INPUT = ResourceLines.line("/day18.txt");

    public static void main(String[] args) {
        Timer.run(() -> {
            final var room = Room.of(PUZZLE_INPUT, 40);
            System.out.println("Part one - room has " + room.safeTileCount() + " safe tiles.");
        });

        Timer.run(() -> {
            final var veryBigRoom = Room.of(PUZZLE_INPUT, 400_000);
            System.out.println("Part two - very big room has " + veryBigRoom.safeTileCount() + " safe tiles.");
        });
    }
}
