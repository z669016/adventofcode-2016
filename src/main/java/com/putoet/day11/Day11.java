package com.putoet.day11;

import java.util.Set;

public class Day11 {
    private static final Generator dg = new Generator("dilithium");
    private static final Microchip dm = new Microchip("dilithium");
    private static final Generator eg = new Generator("elerium");
    private static final Microchip em = new Microchip("elerium");
    private static final Generator cg = new Generator("curium");
    private static final Microchip cm = new Microchip("curium");
    private static final Generator rg = new Generator("ruthenium");
    private static final Microchip rm = new Microchip("ruthenium");
    private static final Generator tg = new Generator("thulium");
    private static final Microchip tm = new Microchip("thulium");
    private static final Generator sg = new Generator("strontium");
    private static final Microchip sm = new Microchip("strontium");
    private static final Generator pg = new Generator("plutonium");
    private static final Microchip pm = new Microchip("plutonium");

    // The first floor contains a strontium generator, a strontium-compatible microchip, a plutonium generator, and a plutonium-compatible microchip.
    // The second floor contains a thulium generator, a ruthenium generator, a ruthenium-compatible microchip, a curium generator, and a curium-compatible microchip.
    // The third floor contains a thulium-compatible microchip.
    // The fourth floor contains nothing relevant.
    private static final Floor[] floors = new Floor[]{
            new Floor(0, Set.of(sg, sm, pg, pm)),
            new Floor(1, Set.of(tg, rg, rm, cg, cm)),
            new Floor(2, Set.of(tm)),
            new Floor(3, Set.of())
    };

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final long start = System.currentTimeMillis();
        System.out.println("the minimum number of steps required to bring all of the objects to the fourth floor is " +
                Solver.solve(floors));
        final long end = System.currentTimeMillis();
        System.out.println("This search took " + duration(start, end));
    }

    private static void part2() {
        final Floor[] part2 = new Floor[]{
                floors[0].add(Set.of(dg, dm, eg, em)).get(),
                floors[1],
                floors[2],
                floors[3]};

        final long start = System.currentTimeMillis();
        System.out.println("the minimum number of steps required to bring all of the objects to the fourth floor is " +
                Solver.solve(part2));
        final long end = System.currentTimeMillis();
        System.out.println("This search took " + duration(start, end));
    }

    private static String duration(long start, long end) {
        return (end-start) > 1000L ? (end - start) + " ms." : (end - start) / 1000 + "sec.";
    }
}
