package com.putoet.day17;

import com.putoet.utils.Timer;

import java.util.stream.Collectors;

public class Day17 {
    public static void main(String[] args) {
        final var finder = new RouteFinder();
        final var passcode = new Passcode("vkjiggvb");

        final var routes = Timer.run(() -> finder.solve(
                new RouteFinder.RouteProblemCase(
                        new Me(),
                        "",
                        new PasscodeDirection(passcode::forRoute)
                ))
        );

        final var groupedRoutes = routes.stream().collect(Collectors.groupingBy(String::length));
        final var minLength = groupedRoutes.keySet().stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow();

        System.out.println("Minimal length is " + minLength);
        System.out.println("Routes are: " + groupedRoutes.get(minLength));
        System.out.println();

        final var maxLength = groupedRoutes.keySet().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();

        System.out.println("Maximal length is " + maxLength);
        System.out.println("Routes are: " + groupedRoutes.get(maxLength));
    }
}
