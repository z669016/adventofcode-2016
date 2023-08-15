package com.putoet.day1;

import com.putoet.grid.Point;

import java.util.*;

public class Location {
    public enum Direction {
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

    public enum Turn {
        RIGHT, LEFT;

        static Turn from(char c) {
            return switch (c) {
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                default -> throw new IllegalArgumentException("Invalid turn:" + c);
            };
        }
    }

    private Direction facing = Direction.NORTH;
    private final Set<Point> trace = new HashSet<>();
    private Point current = Point.ORIGIN;
    private final List<Point> doubles = new ArrayList<>();

    public Location() {
        trace.add(current);
    }

    public Direction facing() {
        return facing;
    }

    public Location move(Turn turn, int distance) {
        facing = facing.turn(turn);

        while (distance-- > 0) {
            current = current.add(facing.asPoint());

            if (trace.contains(current))
                doubles.add(current);
            else
                trace.add(current);
        }

        return this;
    }

    public int distance() {
        return current.manhattanDistance();
    }

    public List<Point> doubles() {
        return doubles;
    }
}
