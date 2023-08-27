package com.putoet.day13;

import com.putoet.grid.Point;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Finder {
    private final Maze maze;

    public Finder(Maze maze) {
        this.maze = maze;
    }

    public Optional<FoundState> locate(Point from, Point to) {
        return find(from, state -> state.point().equals(to));
    }

    public Optional<FoundState> distinct(Point from, int steps) {
        return find(from, state -> state.steps() == steps);
    }

    public Optional<FoundState> find(Point from, Predicate<SearchState> done) {
        final var visited = new HashSet<>(Set.of(from));
        final var queue = new LinkedList<SearchState>();
        queue.offer(new SearchState(0, from));

        var state = queue.poll();
        while (state != null) {
            final var current = state;

            if (done.test(current))
                return Optional.of(new FoundState(current.steps(), visited));

            next(state.point()).stream()
                    .filter(visited::add)
                    .forEach(p -> queue.offer(new SearchState(current.steps() + 1, p)));

            state = queue.poll();
        }

        return Optional.empty();
    }

    private List<Point> next(Point from) {
        return Stream.of(from.add(Point.NORTH), from.add(Point.WEST), from.add(Point.SOUTH), from.add(Point.EAST))
                .filter(p -> p.x() >= 0 && p.y() >= 0)
                .filter(p -> !maze.isWall(p.x(), p.y()))
                .toList();
    }
}
