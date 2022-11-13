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

    public Set<Device> generators() {
        return generators(devices);
    }

    public Set<Device> microchips() {
        return microchips(devices);
    }

    public boolean isValid() {
        return isValid(devices);
    }

    public static boolean isValid(Set<Device> devices) {
        assert devices != null;

        if (devices.size() < 2)
            return true;

        final Set<Device> generators = generators(devices);
        final Set<Device> microchips = microchips(devices);

        if (microchips.isEmpty() || generators.isEmpty())
            return true;

        for (Device device : microchips) {
            if (!hasGenerator(generators, device.name()))
                return false;
        }

        return true;
    }

    public static Set<Device> generators(Set<Device> devices) {
        return devices.stream().filter(device -> device instanceof Generator).collect(Collectors.toSet());
    }

    public static Set<Device> microchips(Set<Device> devices) {
        return devices.stream().filter(device -> device instanceof Microchip).collect(Collectors.toSet());
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

    public boolean containsGenerator(String name) {
        return generators().stream().anyMatch(device -> name.equals(device.name()));
    }

    public boolean containsMicrochip(String name) {
        return microchips().stream().anyMatch(device -> name.equals(device.name()));
    }
}
