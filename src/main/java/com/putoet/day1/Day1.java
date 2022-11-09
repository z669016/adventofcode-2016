package com.putoet.day1;

import com.putoet.resources.CSV;
import org.javatuples.Pair;

public class Day1 {
    public static void main(String[] args) {
        final var directions = CSV.flatList("/day1.txt");
        final var drop = new Location();

        directions.stream()
                .map(directionDistance -> {
                    directionDistance = directionDistance.trim();
                    final Location.Turn turn = Location.Turn.from(directionDistance.charAt(0));
                    final int distance = Integer.parseInt(directionDistance.substring(1));
                    return Pair.with(turn, distance);
                })
                .forEach(dd -> drop.move(dd.getValue0(), dd.getValue1()));

        System.out.println("Distance to HQ is " + drop.distance());
        System.out.println("Distance to first double is " + drop.doubles().get(0).manhattanDistance());
    }
}
