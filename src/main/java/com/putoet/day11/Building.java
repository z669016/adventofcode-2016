package com.putoet.day11;

import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public record Building(int count, int elevator, Floor[] floors, Building prev) {

    public Building {
        assert floors != null && floors.length == 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building building)) return false;
        return elevator == building.elevator && Floors.equals(floors, building.floors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elevator, floors);
    }

    public boolean done() {
        return Floors.done(floors);
    }

    public List<String> devices() {
        return Floors.devices(floors);
    }

    public int count() { return count; }

    public List<Building> nextOptions() {
        final List<Building> nextOptions = new ArrayList<>();

        final Set<Pair<Set<Device>, Set<Device>>> options = optionsToTake(floors[elevator].devices());
        final boolean hasDoubleMoves = options.stream().anyMatch(pair -> pair.getValue0().size() > 1);
        final boolean hasSingleMoves = options.stream().anyMatch(pair -> pair.getValue0().size() == 1);

        // Add options to the queue
        for (Pair<Set<Device>, Set<Device>> option : options) {
            // Only consider downward options is the floors below are not empty
            if (!previousAreEmpty(elevator)) {
                // Only consider downward if the elevator is at floor 1 or up
                if (elevator > 0) {
                    // If there are options to move 1 item down, then don't consider to move two!
                    if (!hasSingleMoves || option.getValue0().size() == 1) {
                        final Optional<Floor> newFloor = floors[elevator - 1].add(option.getValue0());
                        if (newFloor.isPresent()) {
                            final Floor[] newFloors = new Floor[]{floors[0], floors[1], floors[2], floors[3]};
                            newFloors[elevator] = new Floor(elevator, option.getValue1());
                            newFloors[elevator - 1] = newFloor.get();
                            nextOptions.add(new Building(count + 1, elevator - 1, newFloors, this));
                        }
                    }
                }
            }

            // Consider upward options if the elevator isn't at the top floor
            if (elevator < floors.length - 1) {
                // If there are options to move 2 items up, then don't consider to move just one
                if (!hasDoubleMoves || option.getValue0().size() > 1) {
                    final Optional<Floor> newFloor = floors[elevator + 1].add(option.getValue0());
                    if (newFloor.isPresent()) {
                        final Floor[] newFloors = new Floor[]{floors[0], floors[1], floors[2], floors[3]};
                        newFloors[elevator] = new Floor(elevator, option.getValue1());
                        newFloors[elevator + 1] = newFloor.get();
                        nextOptions.add(new Building(count + 1, elevator + 1, newFloors, this));
                    }
                }
            }
        }

        return nextOptions;
    }

    private boolean previousAreEmpty(int elevator) {
        for (int i = 0; i < elevator; i++)
            if (!floors[i].devices().isEmpty())
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
            for (String device : devices()) {
                sb.append(floors[i].contains(device) ? device : ". ").append(" ");
            }
            sb.append("\n");
        }

        if (prev != null) {
            sb.append("\n").append(prev);
        }

        return sb.toString();
    }

    private static Set<Device> take(Device take, Set<Device> from) {
        return from.stream().filter(d -> !take.equals(d)).collect(Collectors.toSet());
    }

    public static Set<Pair<Set<Device>, Set<Device>>> optionsToTake(Set<Device> devices) {
        // Short circuit for an empty set
        if (devices.size() == 0)
            return Set.of();

        // Short circuit for a set of 1
        if (devices.size() == 1)
            return Set.of(new Pair<>(devices, Set.of()));

        // create the result set
        final Set<Pair<Set<Device>, Set<Device>>> options = new HashSet<>();

        // Take options with only 1 item to remove
        final Set<Pair<Device, Set<Device>>> takeOne = takeOne(devices);

        // Add the single options and generate the options moving two devices in teh same run
        takeOne.stream()
                .peek(pair -> options.add(new Pair<>(Set.of(pair.getValue0()), pair.getValue1())))
                .forEach(pair -> takeOne(pair.getValue1())
                        .forEach(sub -> options.add(new Pair<>(Set.of(pair.getValue0(), sub.getValue0()), sub.getValue1()))));

        // Only return valid options, so check to make sure
        return options.stream()
                .filter(pair -> Floor.isValid(pair.getValue0()) && Floor.isValid(pair.getValue1()))
                .collect(Collectors.toSet());
    }

    private static boolean containsGenerator(String name, Set<Device> devices) {
        for (Device device : devices){
            if (device instanceof Generator && device.name().equals(name))
                return true;
        }

        return false;
    }

    private static boolean containsMicrochip(String name, Set<Device> devices) {
        for (Device device : devices){
            if (device instanceof Microchip && device.name().equals(name))
                return true;
        }

        return false;
    }

    private static Set<Pair<Device, Set<Device>>> takeOne(Set<Device> devices) {
        // Short circuit an empty set
        if (devices.size() == 0)
            return Set.of();

        boolean microchipLeavingGenerator = false;
        boolean generatorLeavingMicrochip = false;

        // Add the single choices, but if there are multiple alike (e.g. move a microchip and leave
        // its generator) then only use the first one in the list
        final Set<Pair<Device, Set<Device>>> options = new HashSet<>();
        for (Device device : devices) {
            final Set<Device> leaving = take(device, devices);
            if (device instanceof Microchip && containsGenerator(device.name(), leaving)) {
                if (!microchipLeavingGenerator) {
                    microchipLeavingGenerator = true;
                    options.add(new Pair<>(device, leaving));
                }
            } else if (device instanceof Generator && containsMicrochip(device.name(), leaving)) {
                if (!generatorLeavingMicrochip) {
                    generatorLeavingMicrochip = true;
                    options.add(new Pair<>(device, leaving));
                }
            } else {
                options.add(new Pair<>(device, leaving));

            }
        }

        return options;
    }
}
