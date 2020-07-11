package com.putoet.day15;

import java.util.function.Function;

public class Disk {
    private static int counter = 0;

    private final int id;
    private final int positions;
    private final int start;

    private Disk(int id, int positions, int start) {
        this.id = id;
        this.positions = positions;
        this.start = start;
    }

    Function<Integer,Integer> position() {
        return t -> (id + start + t) % positions;
    }

    public static void reset() {
        counter = 0;
    }

    public static Disk of(int positions, int start){
        return new Disk(++counter, positions, start);
    }
}
