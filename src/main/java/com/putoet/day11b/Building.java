package com.putoet.day11b;

import org.javatuples.Pair;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.stream.Collectors;

public record Building(int count, int elevator, Floors floors, Building prev) {

    public boolean done() {
        return floors.done();
    }

    public List<Device> devices() {
        return floors.devices();
    }

    public int count() {
        return count;
    }

    public List<Building> nextOptions() {
        final List<Building> next = new ArrayList<>();
        final Set<Pair<Set<Device>, Set<Device>>> options = optionsToTake(floors.get(elevator).devices());

        if (elevator < floors.length() - 1) {
            // Create a set to remove "functional" duplicates
            final var upSet = options.stream()
                    .map(opt -> Pair.with(opt.getValue0().size(), floors.up(elevator, opt)))
                    .filter(f -> f.getValue1().isPresent())
                    .map(f -> Pair.with(f.getValue0(), f.getValue1().get()))
                    .collect(Collectors.toSet());

            // Add the unique options to the list
            final var doubles = upSet.stream().anyMatch(f -> f.getValue0() > 1);
            upSet.stream()
                    .filter(f -> !doubles || f.getValue0() > 1)
                    .map(Pair::getValue1)
                    .map(af -> new Building(count + 1, elevator + 1, af, this))
                    .forEach(next::add);
        }

        if (elevator > 0) {
            if (!previousAreEmpty(elevator)) {
                final var downSet = options.stream()
                        .map(opt -> Pair.with(opt.getValue0().size(), floors.down(elevator, opt)))
                        .filter(f -> f.getValue1().isPresent())
                        .map(f -> Pair.with(f.getValue0(), f.getValue1().get()))
                        .collect(Collectors.toSet());

                final var singles = downSet.stream().anyMatch(f -> f.getValue0() == 1);
                downSet.stream()
                        .filter(f -> singles && f.getValue0() == 1)
                        .map(Pair::getValue1)
                        .map(af -> new Building(count + 1, elevator - 1, af, this))
                        .forEach(next::add);
            }
        }
        return next;
    }

    private boolean previousAreEmpty(int elevator) {
        for (int i = 0; i < elevator; i++)
            if (!floors.get(i).devices().isEmpty())
                return false;

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Building count is ").append(count).append("\n");
        for (int i = 3; i >= 0; i--) {
            sb.append("F").append(i + 1);
            sb.append(elevator == i ? " E " : " . ");
            for (Device device : devices()) {
                sb.append(floors.get(i).contains(device) ? device.code() : ". ").append(" ");
            }
            sb.append("\n");
        }

        if (prev != null) {
            sb.append("\n").append(prev);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building building)) return false;
        return elevator == building.elevator && floors.equals(building.floors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elevator, floors);
    }

    public static Set<Pair<Set<Device>, Set<Device>>> optionsToTake(Set<Device> devices) {
        // Short circuit for an empty set
        if (devices.size() == 0)
            return Set.of();

        // Short circuit for a set of 1
        if (devices.size() == 1)
            return Set.of(new Pair<>(devices, Set.of()));

        List<List<Device>> options = new ArrayList<>();
        for (var option : Generator.combination(devices).simple(1))
            options.add(option);
        for (var option : Generator.combination(devices).simple(2))
            options.add(option);

        return options.stream()
                .map(list -> Pair.with(Set.copyOf(list), take(list, devices)))
                .filter(p -> Floor.isValid(p.getValue1()))
                .collect(Collectors.toSet());
    }

    private static Set<Device> take(List<Device> take, Set<Device> from) {
        return from.stream().filter(d -> !take.contains(d)).collect(Collectors.toSet());
    }
}
