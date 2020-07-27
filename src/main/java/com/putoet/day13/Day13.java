package com.putoet.day13;

import com.putoet.utils.maze.Maze;
import com.putoet.utils.maze.Point;
import com.putoet.utils.maze.Route;

import java.util.List;

public class Day13 {
    private static final Point STARTING_POINT = Point.of(1, 1);
    private static final Point TARGET_POINT = Point.of(31,39);

    public static void main(String[] args) {
        final int officeDesignerFavouriteNumber = 1352;
        final Maze maze = new OfficeDesignerMaze(officeDesignerFavouriteNumber);
        final RouteFinder finder = new RouteFinder(maze);

        final List<Route> routes = finder.find(STARTING_POINT, TARGET_POINT);

        System.out.println("Found " + routes.size() + " routes.");
        routes.forEach(route -> {
            System.out.println("Steps " + (route.length() - 1));
            maze.draw(route);
        });

        final int steps = 50;
        System.out.println("Distinct points after " + steps + " is " + finder.distinct(STARTING_POINT, steps));
    }
}
