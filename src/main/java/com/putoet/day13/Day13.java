package com.putoet.day13;

import com.putoet.grid.Point;

public class Day13 {
    private static final Point STARTING_POINT = Point.of(1, 1);
    private static final Point TARGET_POINT = Point.of(31,39);

    public static void main(String[] args) {
        final int officeDesignerFavouriteNumber = 1352;
        final var maze = new Maze(officeDesignerFavouriteNumber);
        final Finder finder = new Finder(maze);

        var found = finder.locate(STARTING_POINT, TARGET_POINT);
        if (found.isEmpty())
            System.out.println("Part 1 could not be solved");
        else
            System.out.println("Part 1 finding " + TARGET_POINT + " took " + found.get().getValue0() + " steps");

        found = finder.distinct(STARTING_POINT, 50);
        if (found.isEmpty())
            System.out.println("Part 2 could not be solved");
        else
            System.out.println("Part 2 taking 50 steps visited " + found.get().getValue1().size() + " distinct points");
    }
}
