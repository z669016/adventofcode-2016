package com.putoet.day15;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        final Sculpture sculpture = new Sculpture(List.of(
                Disk.of(13, 1),
                Disk.of(19, 10),
                Disk.of(3, 2),
                Disk.of(7, 1),
                Disk.of(5, 3),
                Disk.of(17, 5)
        ));

        System.out.println("Part 1 - first open is at t: " + findOpen(sculpture));
        sculpture.add(Disk.of(11, 0));
        System.out.println("Part 2 - first open is at t: " + findOpen(sculpture));
    }

    private static int findOpen(Sculpture sculpture) {
        for (int t = 0; t < Integer.MAX_VALUE; t++) {
            if (sculpture.isOpenOn(t)) {
                return t;
            }
        }

        return -1;
    }
}
