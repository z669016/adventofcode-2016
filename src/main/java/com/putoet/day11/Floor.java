package com.putoet.day11;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record Floor(int id, Set<Device> devices) {
    public Floor {
        assert devices != null;
    }

    public Optional<Floor> add(Set<Device> toAdd) {
        final Set<Device> newDevices = new HashSet<>(this.devices);
        newDevices.addAll(toAdd);

        if (isValid(newDevices))
            return Optional.of(new Floor(id, newDevices));

        return Optional.empty();
    }

    public static boolean isValid(Set<Device> devices) {
        assert devices != null;

        if (devices.size() < 2)
            return true;

        final Set<Device> generators = devices.stream().filter(device -> device instanceof Generator).collect(Collectors.toSet());
        final Set<Device> microchips = devices.stream().filter(device -> device instanceof Microchip).collect(Collectors.toSet());

        if (microchips.isEmpty() || generators.isEmpty())
            return true;

        for (Device device : microchips) {
            if (!hasGenerator(generators, device.name()))
                return false;
        }

        return true;
    }

    private static boolean hasGenerator(Set<Device> generators, String name) {
        for (Device device : generators) {
            if (device.name().equals(name))
                return true;
        }

        return false;
    }

    public boolean contains(String code) {
        return devices.stream().anyMatch(device -> code.equals(device.code()));
    }
}
