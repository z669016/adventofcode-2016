package com.putoet.day17;

import java.util.Objects;
import java.util.Optional;

public class Point {
    private final int x, y;

    private Point(int x, int y) {
        assert x >= 0 && x <= 3;
        assert y >= 0 && y <= 3;

        this.x = x;
        this.y = y;
    }

    public int x() { return x; }
    public int y() { return y; }

    public Optional<Point> left() {
        return x == 0 ? Optional.empty() : Optional.of(new Point(x - 1, y));
    }

    public Optional<Point> right() {
        return x < 3 ? Optional.of(new Point(x() + 1, y())) : Optional.empty();
    }

    public Optional<Point> up() {
        return y == 0 ? Optional.empty() : Optional.of(new Point(x, y - 1));
    }

    public Optional<Point> down() {
        return y < 3 ? Optional.of(new Point(x, y + 1)) : Optional.empty();
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public Optional<Point> move(Direction direction) {
        return switch(direction) {
            case UP -> up();
            case RIGHT -> right();
            case DOWN -> down();
            case LEFT -> left();
        };
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
