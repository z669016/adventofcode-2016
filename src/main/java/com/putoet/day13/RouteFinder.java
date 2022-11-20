package com.putoet.day13;

import com.putoet.utils.maze.Maze;
import com.putoet.utils.maze.Point;
import com.putoet.utils.maze.Route;

import java.util.*;
import java.util.stream.Collectors;

public class RouteFinder {
    private static final int MAX_STEPS = 100_000_000;

    private final Maze<String> maze;

    public RouteFinder(Maze<String> maze) {
        assert maze != null;

        this.maze = maze;
    }

    public List<Route> find(Point startingPoint, Point targetPoint) {
        assert startingPoint != null;
        assert targetPoint != null;

        if (maze.isWall(targetPoint.x(), targetPoint.y()))
            return List.of();

        final Set<Point> visited = new HashSet<>(Set.of(startingPoint));
        List<Route> next = new ArrayList<>(List.of(new Route(startingPoint)));
        int count = 0;

        while (next.size() > 0 && !endsAtTarget(next, targetPoint) && count < MAX_STEPS) {
            next = nextStep(next, visited);
            count++;
        }

        if (next.size() > 0 && !endsAtTarget(next, targetPoint))
            throw new IllegalStateException("Target route not found within " + MAX_STEPS + " steps.");

        return filterTargetRoutes(next, targetPoint);
    }

    public int distinct(Point startingPoint, int steps) {
        assert startingPoint != null;
        assert steps > 0;

        final Set<Point> visited = new HashSet<>(Set.of(startingPoint));
        List<Route> foundRoutes = new ArrayList<>(List.of(new Route(startingPoint)));
        int count = 0;

        while (foundRoutes.size() > 0 && count < steps) {
            foundRoutes = nextStep(foundRoutes, visited);
            count++;
        }

        return visited.size();
    }

    protected List<Route> filterTargetRoutes(List<Route> foundRoutes, Point targetPoint) {
        return foundRoutes.stream().filter(route -> route.current().equals(targetPoint)).collect(Collectors.toList());
    }

    protected boolean endsAtTarget(List<Route> foundRoutes, Point targetPoint) {
        return foundRoutes.stream().anyMatch(route -> route.current().equals(targetPoint));
    }

    protected List<Route> nextStep(List<Route> foundRoutes, Set<Point> visited) {
        final List<Route> nextRoutes = new ArrayList<>();

        foundRoutes.forEach(route -> {
            final Point current = route.current();
            final List<Optional<Point>> directions = List.of(current.up(), current.right(), current.down(), current.left());
            directions.stream()
                    .flatMap(Optional::stream)
                    .filter(point -> !visited.contains(point))
                    .filter(point -> !maze.isWall(point.x(), point.y()))
                    .forEach(point -> {
                        visited.add(point);
                        nextRoutes.add(route.add(point));
                    });

        });

        return nextRoutes;
    }
}
