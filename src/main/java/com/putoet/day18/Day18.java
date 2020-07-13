package com.putoet.day18;

public class Day18 {

    public static final String PUZZLE_INPUT = ".^^^.^.^^^.^.......^^.^^^^.^^^^..^^^^^.^.^^^..^^.^.^^..^.^..^^...^.^^.^^^...^^.^.^^^..^^^^.....^....";

    public static void main(String[] args) {
        final Room room = Room.of(PUZZLE_INPUT, 40);
        System.out.println("Part one - room has " + room.safeTileCount() + " safe tiles.");

        final Room veryBigRoom = Room.of(PUZZLE_INPUT, 400_000);
        System.out.println("Part two - very big room has " + veryBigRoom.safeTileCount() + " safe tiles.");
    }
}
