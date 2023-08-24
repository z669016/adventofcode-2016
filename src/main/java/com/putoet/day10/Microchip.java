package com.putoet.day10;

record Microchip(int value) implements Comparable<Microchip> {

    @Override
    public String toString() {
        return "value-" + value;
    }

    @Override
    public int compareTo(Microchip other) {
        return Integer.compare(value, other.value);
    }
}
