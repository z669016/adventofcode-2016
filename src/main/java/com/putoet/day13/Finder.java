package com.putoet.day13;

import com.putoet.grid.Point;
import org.javatuples.Pair;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Finder {
    private final Maze maze;

    public Finder(Maze maze) {
        this.maze = maze;
    }

    public Optional<Pair<Integer,Set<Point>>> locate(Point from, Point to) {
        return find(from, state -> state.getValue1().equals(to));
    }

    public Optional<Pair<Integer,Set<Point>>> distinct(Point from, int steps) {
        return find(from, state -> state.getValue0() == steps);
    }

    public Optional<Pair<Integer,Set<Point>>> find(Point from, Predicate<Pair<Integer,Point>> done) {
        final Set<Point> visited = new HashSet<>(Set.of(from));
        final Queue<Pair<Integer,Point>> queue = new LinkedList<>();
        queue.offer(Pair.with(0, from));

        var state = queue.poll();
        while (state != null) {
            final var current = state;

            if (done.test(current))
                return Optional.of(Pair.with(current.getValue0(), visited));

            next(state.getValue1()).stream()
                    .filter(visited::add)
                    .forEach(p -> queue.offer(Pair.with(current.getValue0() + 1, p)));

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
