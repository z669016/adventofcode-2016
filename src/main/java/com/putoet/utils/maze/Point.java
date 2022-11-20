package com.putoet.utils.maze;

import java.util.Optional;

public record Point(int x, int y) {
    public Point {
        assert x >= 0;
        assert y >= 0;

    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

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
}
