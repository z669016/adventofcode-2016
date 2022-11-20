package com.putoet.utils.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Route {
    private final List<Point> route = new ArrayList<>();

    public Route(Point startingPoint) {
        assert startingPoint != null;

        route.add(startingPoint);
    }

    public Route(Route other, Point point) {
        assert other != null;
        assert point != null;

        this.route.addAll(other.route);
        this.route.add(point);
    }

    public static Route of(Point ... points) {
        assert points != null;
        assert points.length > 0;

        Route route = new Route(points[0]);
        for (int idx = 1; idx < points.length; idx++)
            route = route.add(points[idx]);

        return route;
    }

    public boolean contains(int x, int y) {
        return contains(Point.of(x, y));
    }

    public boolean contains(Point point) {
        return route.contains(point);
    }

    public Route add(int x, int y) {
        return add(Point.of(x, y));
    }

    public Route add(Point point) {
        final Point current = current();
        if (current.x() != point.x() && current.y() != point.y())
            throw new IllegalStateException("Invalid addition to the route. Current for the route is " + current + " while trying to add " + point);

        return new Route(this, point);
    }

    public int maxX() {
        return max(Point::x);
    }

    public int maxY() {
        return max(Point::y);
    }

    private int max(Function<Point,Integer> ref) {
        return route.stream()
                .mapToInt(ref::apply)
                .max()
                .orElse(0);
    }

    public int length() {
        return route.size();
    }

    public Point current() {
        return route.get(length() - 1);
    }

    @Override
    public String toString() {
        return route.toString();
    }
}
