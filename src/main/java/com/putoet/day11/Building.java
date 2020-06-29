package com.putoet.day11;

import java.util.*;

public class Building {
    private final int level;
    private final Map<String,Part> parts = new HashMap<>();

    public Building(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("Floor %s contains %s.", levelAsText(), partsAsText());
    }

    private String levelAsText() {
        switch (level) {
            case 1: return "first";
            case 2: return "second";
            case 3: return "third";
            case 4: return "fourth";
            default:
                return String.valueOf(level);
        }
    }

    private String partsAsText() {
        if (parts.size() == 0)
            return "nothing relevant";

        final StringBuilder sb = new StringBuilder();
        final Iterator<Part> iter = parts.values().iterator();
        boolean first = true;

        while (iter.hasNext()) {
            final Part part = iter.next();

        }

        return sb.toString();
    }

    public static List<Building> day11() {
        final List<List<String>> parts = List.of(
                List.of("strontium generator", "strontium-compatible microchip", "plutonium generator", "plutonium-compatible microchip"),
                List.of("thulium generator", "ruthenium generator", "ruthenium-compatible microchip", "curium generator", "curium-compatible microchip"),
                List.of("thulium-compatible microchip"),
                List.of()
        );

        final List<Building> buildings = new ArrayList<>();
        for (int idx = 0; idx < parts.size(); idx++) {
            buildings.add(day11(idx + 1, parts.get(idx)));
        }
        return buildings;
    }

    public static Building day11(int level, List<String> parts) {
        final Building building = new Building(level);
        parts.forEach(description -> {
            final Part part = createPart(description);
            building.parts.put(part.name(), part);
        });

        return null;
    }

    private static Part createPart(String description) {
        final String name = description.split(" ")[0];
        if (description.endsWith("generator"))
            return new Generator(name);

        return new Microchip(name.split("-")[0]);
    }
}
