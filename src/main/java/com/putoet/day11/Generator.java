package com.putoet.day11;

import java.util.Locale;
import java.util.Objects;

public class Generator extends Device {
    public Generator(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Generator)) return false;

        Generator device = (Generator) o;

        return name().equals(device.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), name());
    }

    @Override
    public String toString() {
        return name().charAt(0) + "g";
    }
}
