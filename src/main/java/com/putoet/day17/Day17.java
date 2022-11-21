package com.putoet.day17;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 {
    public static void main(String[] args) {
        final RouteFinder finder = new RouteFinder();
        final List<String> routes = finder.solve(
                new RouteFinder.RouteProblemCase(
                        new Me(),
                        "",
                        new PasscodeDirection(new Passcode("vkjiggvb")::forRoute)
                ));

        final Map<Integer, List<String>> groupedRoutes =
                routes.stream().collect(Collectors.groupingBy(String::length));

        final int minLength = groupedRoutes.keySet().stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow();

        System.out.println("Minimal length is " + minLength);
        System.out.println("Routes are: " + groupedRoutes.get(minLength));

        final int maxLength = groupedRoutes.keySet().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();

        System.out.println("Maximal length is " + maxLength);
        System.out.println("Routes are: " + groupedRoutes.get(maxLength));
    }
}
