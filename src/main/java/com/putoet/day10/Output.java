package com.putoet.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Output implements Consumer<Microchip> {
    public static final String PREFIX = "output-";

    private final int id;
    private final List<Microchip> microchips = new ArrayList<>();

    public Output(int id) {
        this.id = id;
    }

    @Override
    public void accept(Microchip microchip) {
        microchips.add(microchip);
    }

    public final List<Microchip> list() {
        return Collections.unmodifiableList(microchips);
    }

    public boolean contains(Microchip microchip) {
        return microchips.contains(microchip);
    }

    public int size() { return list().size(); }

    public int id() { return id; }
    public String name() { return PREFIX + id; }

    @Override
    public String toString() {
        return "Output{" +
                "id=" + id +
                ", microchips=" + microchips +
                '}';
    }
}
