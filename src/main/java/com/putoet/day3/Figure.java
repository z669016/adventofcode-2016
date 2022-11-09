package com.putoet.day3;

import java.util.Arrays;
import java.util.List;

public record Figure(int a, int b, int c) {
    public boolean possibleTriangle() {
        return ((a + b) > c) && ((a + c) > b) && ((b + c) > a);
    }

    static Figure from(List<Integer> list) {
        if (list.size() == 3)
            return new Figure(list.get(0), list.get(1), list.get(2));

        throw new IllegalArgumentException("Cannot create a figure for " + list);
    }

    static Figure from(String line) {
        line = line.trim();
        while (line.contains("  "))
            line = line.replace("  ", " ");
        return from(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
    }
}
