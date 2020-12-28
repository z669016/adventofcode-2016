package com.putoet.day11;

import java.util.Objects;

public class Microchip extends Device {
    public Microchip(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Microchip)) return false;

        Microchip device = (Microchip) o;

        return name().equals(device.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), name());
    }

    @Override
    public String toString() {
        return name().charAt(0) + "m";
    }
}
