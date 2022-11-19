package com.putoet.day11b;

import org.javatuples.Pair;

import java.util.*;

public record Floors(Floor[] floors) {
    public Floors {
        assert floors != null && floors.length == 4;
    }

    public Floor get(int index) {
        if (index < 0 || index >= floors.length)
            throw new IndexOutOfBoundsException(index);

        return floors[index];
    }

    public int length() {
        return floors.length;
    }

    public List<Device> devices() {
        return Arrays.stream(floors)
                .map(Floor::devices)
                .flatMap(Collection::stream)
                .toList();
    }

    public boolean done() {
        return floors[0].devices().isEmpty() && floors[1].devices().isEmpty() && floors[2].devices().isEmpty();
    }

    public Optional<Floors> up(int from, Pair<Set<Device>, Set<Device>> option) {
        assert from < floors.length - 1;

        // Moving up one element is only allowed if it's a microchip or RTG and it's partner is not on the same floor
        if (option.getValue0().size() == 1 && option.getValue1().size() > 0) {
            final var device = new ArrayList<>(option.getValue0()).get(0);
            if (option.getValue1().contains(new Microchip(device.name())) || option.getValue1().contains(new Generator(device.name())))
                return Optional.empty();
        }

        final Optional<Floor> updated = floors[from + 1].add(option.getValue0());
        return updated.map(floor -> {
            final Floor[] copy = new Floor[]{floors[0], floors[1], floors[2], floors[3]};
            copy[from] = new Floor(copy[from].id(), option.getValue1());
            copy[from + 1] = floor;

            return Optional.of(new Floors(copy));
        }).orElse(Optional.empty());
    }

    public Optional<Floors> down(int from, Pair<Set<Device>, Set<Device>> option) {
        assert from > 0;

        // never ever bring gown a RTG and it's Microchip
        if (option.getValue0().size() == 2) {
            final var devices = new ArrayList<>(option.getValue0());
            final var d1 = devices.get(0);
            final var d2 = devices.get(1);

            if (d1.name().equals(d2.name()))
                return Optional.empty();
        }

        final Optional<Floor> updated = floors[from - 1].add(option.getValue0());
        return updated.map(floor -> {
            final Floor[] copy = new Floor[]{floors[0], floors[1], floors[2], floors[3]};
            copy[from] = new Floor(copy[from].id(), option.getValue1());
            copy[from - 1] = floor;

            return Optional.of(new Floors(copy));
        }).orElse(Optional.empty());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Floors other)) return false;

        return abstractFloors(floors).equals(abstractFloors(other.floors));
    }

    @Override
    public int hashCode() {
        return abstractFloors(floors).hashCode();
    }

    private static List<Pair<Integer, Integer>> abstractFloors(Floor[] floors) {
        final List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int idx = 0; idx < floors.length; idx++) {
            for (Device device : floors[idx].devices()) {
                if (device instanceof Microchip microchip) {
                    boolean found = false;
                    for (int idy = 0; idy < floors.length && !found; idy++) {
                        for (Device d : floors[idy].devices()) {
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
