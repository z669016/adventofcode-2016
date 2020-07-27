package com.putoet.utils.maze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {
    public static Point[] POINTS = new Point[] {
            Point.of(1, 1),
            Point.of(1, 2),
            Point.of(2, 2),
            Point.of(3, 2),
            Point.of(3, 3),
            Point.of(3, 4),
            Point.of(4, 4),
            Point.of(4, 5),
            Point.of(5, 5),
            Point.of(6, 5),
            Point.of(6, 4),
            Point.of(7, 4)
    };

    private Route route;

    @BeforeEach
    void setup() {
        route = new Route(Point.of(0, 1));
        for (int x = 0; x < 10; x++)
            route = route.add(x, 0);
    }

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Route(null));
        assertThrows(AssertionError.class, () -> new Route(null, Point.of(1,1)));
        assertThrows(AssertionError.class, () -> new Route(new Route(Point.of(1,1)), null));
    }

    @Test
    void length() {
        assertEquals(11, route.length());
    }

    @Test
    void contains() {
        System.out.println(route);
        assertTrue(route.contains(3, 0));
    }

    @Test
    void maxX() {
        assertEquals(9, route.maxX());
    }

    @Test
    void maxY() {
        assertEquals(1, route.maxY());
    }

    @Test
    void add() {
        final Route route = new Route(new Point(0, 1));
        final Route added = route.add(1, 1);

        assertNotSame(route, added);

        assertThrows(IllegalStateException.class, () -> added.add(3, 3));
    }

    @Test
    void of() {
        assertThrows(AssertionError.class, () -> Route.of(null));
        assertThrows(AssertionError.class, () -> Route.of(new Point[] {}));

        final Route route = Route.of(POINTS);
        assertEquals(12, route.length());
        Arrays.stream(POINTS).forEach(point -> assertTrue(route.contains(point)));
    }
}