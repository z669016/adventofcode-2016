package com.putoet.day11;

public class RTG extends Part {
    public RTG(String name) {
        super(name);
    }

    boolean fries(Chip chip) {
        assert chip != null;

        return !this.name().equals(chip.name());
    }
}
