package com.putoet.day11b;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record Floor(int id, Set<Device> devices) {
    public Floor {
        assert devices != null;
        assert isValid(devices);
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

    public boolean contains(Device device) {
        return devices.contains(device);
    }

    public static boolean isValid(Set<Device> devices) {
        assert devices != null;

        if (devices.size() < 2)
            return true;

        for (Device device : devices) {
            if (device.type() == DeviceType.MICROCHIP) {
                boolean rtgRequired = false;
                boolean rtgAvailable = false;
                for (Device other : devices) {
                    if (other.type() == DeviceType.RTG)
                        rtgRequired = true;

                    if (other.type() == DeviceType.RTG && other.name().equals(device.name()))
                        rtgAvailable = true;
                }

                if (rtgRequired && !rtgAvailable)
                    return false;
            }
        }

        return true;
    }

    public static Set<Device> generators(Set<Device> devices) {
        return devices.stream().filter(device -> device instanceof Generator).collect(Collectors.toSet());
    }

    public static Set<Device> microchips(Set<Device> devices) {
        return devices.stream().filter(device -> device instanceof Microchip).collect(Collectors.toSet());
    }

    private static boolean hasGeneratorFor(Microchip microchip, Set<Device> devices) {
        for (Device device : devices) {
            if (device.type() == DeviceType.RTG && device.name().equals(microchip.name()))
                return true;
        }

        return false;
    }
}
