package com.putoet.day17;

class Me {
    Point whereAmI(String route) {
        assert route != null;

        var location = Point.of(0, 0);
        for (var idx = 0; idx < route.length(); idx++) {
            location = (switch (route.charAt(idx)) {
                case 'U' -> location.up();
                case 'D' -> location.down();
                case 'R' -> location.right();
                case 'L' -> location.left();
                default -> throw new IllegalArgumentException("Invalid direction in route '" + route + "'");
            }).orElseThrow(() -> new IllegalArgumentException("Cannot move through the walls, asshole!"));
        }

        return location;
    }
}
