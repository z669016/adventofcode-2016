package com.putoet.day17;

import java.util.List;

class RouteFinder extends Finder<String> {
    static class RouteProblemCase implements State<String> {
        private final Me me;
        private final String route;
        private final PasscodeDirection passcode;

        public RouteProblemCase(Me me, String route, PasscodeDirection passcode) {
            assert me != null;
            assert route != null;
            assert passcode != null;

            this.me = me;
            this.route = route;
            this.passcode = passcode;
        }

        @Override
        public boolean isFinished() {
            return me.whereAmI(route).equals(Point.of(3,3));
        }

        @Override
        public List<? extends State<String>> next() {
            final var directions = passcode.forRoute(route);
            final var current = me.whereAmI(route);

            return directions.stream()
                    .filter(direction -> current.move(direction).isPresent())
                    .map(direction -> new RouteProblemCase(me, route + direction, passcode))
                    .toList();
        }

        @Override
        public String solution() {
            return route;
        }
    }
}
