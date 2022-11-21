package com.putoet.day17;

import org.junit.jupiter.api.Test;

import java.util.List;

class RouteFinderTest {
    @Test
    void solve() {
        final RouteFinder finder = new RouteFinder();

        final List<String> routes = finder.solve(
                new RouteFinder.RouteProblemCase(
                        new Me(),
                        "",
                        new PasscodeDirection(new Passcode("ihgpwlah")::forRoute)
                ));
//        System.out.println(routes);
    }

}