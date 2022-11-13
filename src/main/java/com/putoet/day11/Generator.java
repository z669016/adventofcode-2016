package com.putoet.day11;

import java.util.Objects;

public class Generator extends Device {
    public Generator(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Generator device)) return false;

        return name().equals(device.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), name());
    }

    @Override
    public String type() {
        return "g";
    }
}
