package com.putoet.day13;

import com.putoet.utils.maze.Point;
import com.putoet.utils.maze.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RouteFinderTest {
    private static final Point TARGET_POINT = Point.of(7,4);
    private static final Point STARTING_POINT = Point.of(1, 1);
    private static final int OFFICE_DESIGNER_FAVOURITE_NUMBER = 10;
    private static final OfficeDesignerMaze MAZE = new OfficeDesignerMaze(OFFICE_DESIGNER_FAVOURITE_NUMBER);

    private RouteFinder finder;

    @BeforeEach
    void setup() {
        finder = new RouteFinder(MAZE);
    }

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new RouteFinder(null));
    }

    @Test
    void find() {
        assertThrows(AssertionError.class, () -> finder.find(null, TARGET_POINT));
        assertThrows(AssertionError.class, () -> finder.find(STARTING_POINT, null));

        assertEquals(List.of(), finder.find(STARTING_POINT, Point.of(9,0)));
        assertEquals(List.of(), finder.find(Point.of(1, 8), TARGET_POINT));

        final List<Route> routes = finder.find(STARTING_POINT, TARGET_POINT);
        assertEquals(1, routes.size());
        assertEquals(12, routes.get(0).length());
        assertEquals(TARGET_POINT, routes.get(0).current());
        routes.forEach(route -> MAZE.draw(Optional.of(route), 10, 10));
    }

    @Test
    void distinct() {
        assertThrows(AssertionError.class, () -> finder.distinct(null, 1));
        assertThrows(AssertionError.class, () -> finder.distinct(STARTING_POINT, 0));

        assertEquals(3, finder.distinct(STARTING_POINT, 1));
        assertEquals(5, finder.distinct(STARTING_POINT, 2));
    }

    @Test
    void notContainsTargetRoute() {
        final List<Route> routes = List.of(
                new Route(STARTING_POINT),
                new Route(STARTING_POINT),
                new Route(STARTING_POINT),
                new Route(STARTING_POINT),
                new Route(STARTING_POINT),
                new Route(STARTING_POINT),
                new Route(STARTING_POINT)
        );
        assertFalse(finder.endsAtTarget(routes, TARGET_POINT));
        assertEquals(0, finder.filterTargetRoutes(routes, TARGET_POINT).size());
    }

    @Test
    void containsTargetRoute() {
        final List<Route> routes = List.of(
                new Route(STARTING_POINT),
                new Route(STARTING_POINT),
                new Route(TARGET_POINT),
                new Route(STARTING_POINT),
                new Route(STARTING_POINT),
                new Route(TARGET_POINT),
                new Route(STARTING_POINT)
        );
        assertTrue(finder.endsAtTarget(routes, TARGET_POINT));
        assertEquals(2, finder.filterTargetRoutes(routes, TARGET_POINT).size());
    }

    @Test
    void nextStep() {
        final List<Route> initialRoute = List.of(new Route(STARTING_POINT));
        final Set<Point> visited = new HashSet<>(Set.of(STARTING_POINT));
        List<Route> routes = finder.nextStep(initialRoute, visited);

        assertEquals(2, routes.size());
        System.out.println(routes);

        routes = finder.nextStep(routes, visited);
        assertEquals(2, routes.size());

        routes.forEach(route -> MAZE.draw(Optional.of(route), 10, 10));
    }
}