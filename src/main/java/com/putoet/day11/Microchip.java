package com.putoet.day11;

public class Microchip extends AbstractPart {
    public Microchip(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name + "-compatible microchip";
    }
}
