package com.putoet.day17;

import java.util.Optional;

public record Point(int x, int y) {
    public Point {
        assert x >= 0 && x <= 3;
        assert y >= 0 && y <= 3;
    }

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
}
