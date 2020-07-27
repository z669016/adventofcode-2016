package com.putoet.utils.maze;

import java.util.Objects;
import java.util.Optional;

public class Point {
    private final int x, y;

    public Point(int x, int y) {
        assert x >= 0;
        assert y >= 0;

        this.x = x;
        this.y = y;
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public int x() { return x; }
    public int y() { return y; }

    public Optional<Point> left() {
        return x == 0 ? Optional.empty() : Optional.of(new Point(x - 1, y));
    }

    public Optional<Point> right() {
        return Optional.of(new Point(x + 1, y));
    }

    public Optional<Point> up() {
        return y == 0 ? Optional.empty() : Optional.of(new Point(x, y - 1));
    }

    public Optional<Point> down() {
        return Optional.of(new Point(x, y + 1));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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
}
