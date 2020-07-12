package com.putoet.day17;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 {
    public static void main(String[] args) {
        final RouteFinder finder = new RouteFinder();
        final List<String> routes = finder.solveAll(
                new RouteFinder.RouteProblemCase(
                        new Me(),
                        "",
                        new PasscodeDirection(new Passcode("vkjiggvb")::forRoute)
                ));

        final Map<Integer, List<String>> groupedRoutes =
                routes.stream().collect(Collectors.groupingBy(String::length));

        final OptionalInt minLength = groupedRoutes.keySet().stream()
                .mapToInt(Integer::intValue)
                .min();

        final OptionalInt maxLength = groupedRoutes.keySet().stream()
                .mapToInt(Integer::intValue)
                .max();

        if (minLength.isPresent()) {
            System.out.println("Minimal length is " + minLength.getAsInt());
            System.out.println("Routes are: " + groupedRoutes.get(minLength.getAsInt()));
        }

        if (minLength.isPresent()) {
            System.out.println("Maximal length is " + maxLength.getAsInt());
            System.out.println("Routes are: " + groupedRoutes.get(maxLength.getAsInt()));
        }
    }
}
