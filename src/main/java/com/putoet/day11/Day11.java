package com.putoet.day11;

import java.util.Set;

public class Day11 {
    public static final String DILITHIUM = "dilithium";
    public static final String ELERIUM = "elerium";
    public static final String CURIUM = "curium";
    public static final String RUTHENIUM = "ruthenium";
    public static final String THULIUM = "thulium";
    public static final String STRONTIUM = "strontium";
    public static final String PLUTONIUM = "plutonium";

    private static final Generator dg = new Generator(DILITHIUM);
    private static final Microchip dm = new Microchip(DILITHIUM);
    private static final Generator eg = new Generator(ELERIUM);
    private static final Microchip em = new Microchip(ELERIUM);
    private static final Generator cg = new Generator(CURIUM);
    private static final Microchip cm = new Microchip(CURIUM);
    private static final Generator rg = new Generator(RUTHENIUM);
    private static final Microchip rm = new Microchip(RUTHENIUM);
    private static final Generator tg = new Generator(THULIUM);
    private static final Microchip tm = new Microchip(THULIUM);
    private static final Generator sg = new Generator(STRONTIUM);
    private static final Microchip sm = new Microchip(STRONTIUM);
    private static final Generator pg = new Generator(PLUTONIUM);
    private static final Microchip pm = new Microchip(PLUTONIUM);

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
                floors[0].add(Set.of(dg, dm, eg, em)).orElseThrow(),
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
        return (end - start) > 1000L ? (end - start) + " ms." : (end - start) / 1000 + "sec.";
    }
}
