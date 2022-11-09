package com.putoet.day1;

import com.putoet.grid.Point;

import java.util.*;

public class Location {
    enum Direction {
        NORTH, WEST, SOUTH, EAST;

        Direction turn(Turn turn) {
            return (turn == Turn.RIGHT) ? switch (this) {
                case NORTH -> WEST;
                case WEST -> SOUTH;
                case SOUTH -> EAST;
                default -> NORTH;
            } : switch (this) {
                case NORTH -> EAST;
                case WEST -> NORTH;
                case SOUTH -> WEST;
                default -> SOUTH;
            };
        }

        Point asPoint() {
            return switch (this) {
                case NORTH -> Point.NORTH;
                case WEST -> Point.WEST;
                case SOUTH -> Point.SOUTH;
                case EAST -> Point.EAST;
            };
        }
    }

    enum Turn {
        RIGHT, LEFT
    }

    private Direction facing = Direction.NORTH;
    private final List<Integer> moved = new ArrayList<>(List.of(0, 0, 0, 0));
    private final Set<Point> trace = new HashSet<>();
    private Point current = new Point(0, 0);
    private final List<Point> doubles = new ArrayList<>();

    public Location() {
        trace.add(current);
    }

    public Direction facing() {
        return facing;
    }

    public void move(Turn turn, int distance) {
        facing = facing.turn(turn);
        moved.set(facing.ordinal(), moved.get(facing.ordinal()) + distance);
        traceMove(distance);
    }

    private void traceMove(int distance) {
        while (distance-- > 0) {
            current = current.add(facing.asPoint());

            if (trace.contains(current))
                doubles.add(current);
            else
                trace.add(current);
        }
    }

    public List<Integer> moved() {
        return Collections.unmodifiableList(moved);
    }

    public int distance() {
        return Math.abs(moved.get(0) - moved.get(2)) + Math.abs(moved.get(1) - moved.get(3));
    }

    public List<Point> doubles() {
        return doubles;
    }

    public void move(String directionDistance) {
        assert directionDistance != null;

        directionDistance = directionDistance.trim();
        assert directionDistance.length() > 1;
        assert directionDistance.charAt(0) == 'L' || directionDistance.charAt(0) == 'R';

        final Turn turn = directionDistance.charAt(0) == 'L' ? Turn.LEFT : Turn.RIGHT;
        final int distance = Integer.parseInt(directionDistance.substring(1));

        move(turn, distance);
    }

    public void move(List<String> directionDistanceList) {
        directionDistanceList.forEach(this::move);
    }
}
