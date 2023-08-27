package com.putoet.day15;

import java.util.function.Function;

record Disk(int id, int positions, int start) {
    private static int counter = 0;

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
