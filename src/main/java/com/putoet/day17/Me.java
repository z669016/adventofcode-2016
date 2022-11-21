package com.putoet.day17;

import java.util.Optional;

public class Me {
    Point whereAmI(String route) {
        assert route != null;

        Point location = Point.of(0, 0);
        for (int idx = 0; idx < route.length(); idx++) {
            Optional<Point> newPoint = switch (route.charAt(idx)) {
                case 'U' -> location.up();
                case 'D' -> location.down();
                case 'R' -> location.right();
                case 'L' -> location.left();
                default -> throw new IllegalArgumentException("Invalid direction in route '" + route + "'");
            };
            if (newPoint.isPresent())
                location = newPoint.get();
            else
                throw new IllegalArgumentException("Cannot move through the walls, asshole!");
        }

        return location;
    }
}
