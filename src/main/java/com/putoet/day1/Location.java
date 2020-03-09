package com.putoet.day1;

import java.util.*;

public class Location {
    enum Direction {
        NORTH, WEST, SOUTH, EAST;

        Direction turn(Turn turn) {
            if (turn == Turn.RIGHT) {
                switch (this) {
                    case NORTH: return WEST;
                    case WEST: return SOUTH;
                    case SOUTH: return EAST;
                    default: return NORTH;
                }
            } else {
                switch (this) {
                    case NORTH: return EAST;
                    case WEST: return NORTH;
                    case SOUTH: return WEST;
                    default: return SOUTH;
                }
            }
        }
    }

    enum Turn {
        RIGHT, LEFT
    }

    static class Point {
        private final int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point move(Direction facing) {
            switch (facing) {
                case NORTH: return new Point(x, y + 1);
                case WEST: return new Point(x + 1, y);
                case SOUTH: return new Point(x, y - 1);
                default:  return new Point(x - 1, y);
            }
        }

        public int distance() {
            return Math.abs(x) + Math.abs(y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private Direction facing = Direction.NORTH;
    private List<Integer> moved = new ArrayList<>(List.of(0, 0, 0, 0));
    private Set<Point> trace = new HashSet<>();
    private Point current = new Point(0, 0);
    private List<Point> doubles = new ArrayList<>();

    public Location() {
        trace.add(current);
    }

    public Direction facing() { return facing; }

    public void move(Turn turn, int distance) {
        facing = facing.turn(turn);
        moved.set(facing.ordinal(), moved.get(facing.ordinal()) + distance);
        traceMove(distance);
    }

    private void traceMove(int distance) {
        while (distance-- > 0) {
            current = current.move(facing);

            if (trace.contains(current))
                doubles.add(current);
            else
                trace.add(current);
        }
    }

    public List<Integer> moved() { return Collections.unmodifiableList(moved); }

    public int distance() {
        return Math.abs(moved.get(0) - moved.get(2)) + Math.abs(moved.get(1) - moved.get(3));
    }

    public List<Point> doubles() { return doubles; }

    public void move(String directionDistance) {
        assert directionDistance != null;
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
