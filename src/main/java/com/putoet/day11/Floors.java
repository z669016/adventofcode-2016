package com.putoet.day11;

import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Floors {
    public static List<String> devices(Floor[] floors) {
        return Arrays.stream(floors)
                .map(Floor::devices)
                .flatMap(Collection::stream)
                .map(Device::code)
                .sorted()
                .toList();
    }

    public static boolean done(Floor[] floors) {
        return floors[0].devices().isEmpty() && floors[1].devices().isEmpty() && floors[2].devices().isEmpty();
    }

    public static int hashCode(Floor[] floors) {
        return Arrays.hashCode(floors);
    }

    public static boolean equals(Floor[] first, Floor[] second) {
        return abstractFloors(first).equals(abstractFloors(second));
    }

    private static List<Pair<Integer,Integer>> abstractFloors(Floor[] floors) {
        final List<Pair<Integer,Integer>> list = new ArrayList<>();
        for (int idx = 0; idx < floors.length; idx++) {
            for (Device device : floors[idx].devices()) {
                if (device instanceof Microchip microchip) {
                    boolean found = false;
                    for (int idy = 0; idy < floors.length && !found; idy++) {
                        for (Device d : floors[idx].devices()) {
                            if (d instanceof Generator generator) {
                                if (microchip.name().equals(generator.name())) {
                                    list.add(Pair.with(idx, idy));
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(list);
        return list;
    }

}
