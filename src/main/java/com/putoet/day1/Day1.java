package com.putoet.day1;

import com.putoet.resources.CSV;
import com.putoet.utils.Timer;

public class Day1 {
    record Move(Location.Turn turn, int distance) {
    }

    public static void main(String[] args) {
        final var moves = CSV.flatList("/day1.txt").stream()
                .map(directionDistance -> {
                    directionDistance = directionDistance.trim();
                    final Location.Turn turn = Location.Turn.from(directionDistance.charAt(0));
                    final int distance = Integer.parseInt(directionDistance.substring(1));
                    return new Move(turn, distance);
                })
                .toList();

        Timer.run(() -> {
            final var drop = moves.stream()
                    .collect(Location::new,
                            (l, m) -> l.move(m.turn, m.distance),
                            (l1, l2) -> {
                                throw new IllegalStateException("Cannot combine this stream");
                            });

            System.out.println("Distance to HQ is " + drop.distance());
            System.out.println("Distance to first double is " + drop.doubles().get(0).manhattanDistance());
        });
    }
}
