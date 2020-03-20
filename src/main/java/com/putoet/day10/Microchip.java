package com.putoet.day10;

import java.util.Objects;

public class Microchip implements Comparable<Microchip> {
    private final int value;

    public Microchip(int value) {
        this.value = value;
    }

    public int value() { return value; }

    @Override
    public String toString() {
        return "value-" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Microchip)) return false;
        Microchip microchip = (Microchip) o;
        return value == microchip.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Microchip other) {
        return Integer.compare(value, other.value);
    }
}
