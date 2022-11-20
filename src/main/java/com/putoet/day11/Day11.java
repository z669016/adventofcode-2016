package com.putoet.day11;

import org.javatuples.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Day11 {
    // https://github.com/romellem/advent-of-code/blob/master/2016/11/notes.txt

    public static void main(String[] args) {
        part(new Floor(0, List.of(
                Pair.with(2, 2),
                Pair.with(2, 3),
                Pair.with(1, 0),
                Pair.with(0, 0)
        )));
        part(new Floor(0, List.of(
                Pair.with(4, 4),
                Pair.with(2, 3),
                Pair.with(1, 0),
                Pair.with(0, 0)
        )));
    }

    public static void part(Floor floor) {
        var state = solve(floor);
        if (state == null)
            System.out.println("Puzzle with " + floor + " could not be solved.");
        else
            System.out.println("Puzzle with " + floor + " solved in " + state.steps() + " steps.");
    }

    public static State solve(Floor floor) {
        final var queue = new PriorityQueue<State>();
        final var history = new HashSet<Floor>();

        queue.offer(new State(0, floor, null));
        history.add(floor);

        var state = queue.poll();
        while (state != null && !state.floor().done()) {
            final var next = state.floor().next();
            final var current = state;

            next.stream()
                    .filter(history::add)
                    .forEach(f -> queue.offer(new State(current.steps() + 1, f, current)));
            state = queue.poll();
        }
        return state;
    }
}
