package com.putoet.day11;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Floor {
    private final int id;
    private final Set<Device> devices;

    public Floor(int id, Set<Device> devices) {
        this.id = id;
        this.devices = devices;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Floor)) return false;

        Floor floor = (Floor) o;

        if (id != floor.id) return false;
        return devices.equals(floor.devices);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + devices.hashCode();
        return result;
    }

    private static boolean hasGenerator(Set<Device> generators, String name) {
        for (Device device : generators) {
            if (device.name().equals(name))
                return true;
        }

        return false;
    }

    public Set<Device> devices() {
        return devices;
    }

    public boolean isValid() {
        return isValid(devices);
    }

    public boolean contains(String code) {
        return devices.stream().anyMatch(device -> code.equals(device.code()));
    }
}
