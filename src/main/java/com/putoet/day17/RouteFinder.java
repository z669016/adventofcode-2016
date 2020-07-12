package com.putoet.day17;

import com.putoet.utils.BacktrackProblemSolver;

import java.util.List;
import java.util.stream.Collectors;

public class RouteFinder extends BacktrackProblemSolver<String> {
    public static class RouteProblemCase implements BacktrackState<String> {
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
        public List<? extends BacktrackState<String>> next() {
            final List<Direction> directions = passcode.forRoute(route);
            final Point current = me.whereAmI(route);

            return directions.stream()
                    .filter(direction -> current.move(direction).isPresent())
                    .map(direction -> new RouteProblemCase(me, route + direction.toString(), passcode))
                    .collect(Collectors.toList());
        }

        @Override
        public String solution() {
            return route;
        }
    }
}
