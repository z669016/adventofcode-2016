package com.putoet.day13;

import com.putoet.grid.Point;
import com.putoet.utils.Timer;

public class Day13 {
    private static final Point STARTING_POINT = Point.of(1, 1);
    private static final Point TARGET_POINT = Point.of(31, 39);

    public static void main(String[] args) {
        final var officeDesignerFavouriteNumber = 1352;
        final var maze = new Maze(officeDesignerFavouriteNumber);
        final var finder = new Finder(maze);

        Timer.run(() -> {
            var found = finder.locate(STARTING_POINT, TARGET_POINT).orElseThrow();
            System.out.println("Part 1 finding " + TARGET_POINT + " took " + found.steps() + " steps");
        });

        Timer.run(() -> {
            var found = finder.distinct(STARTING_POINT, 50).orElseThrow();
            System.out.println("Part 2 taking 50 steps visited " + found.points().size() + " distinct points");
        });
    }
}
