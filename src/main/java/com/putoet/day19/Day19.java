package com.putoet.day19;

import com.putoet.math.Josephus;
import com.putoet.utils.Timer;

import java.util.*;

public class Day19 {
    public static void main(String[] args) {
        final var puzzleInput = 3004953;

        Timer.run(() ->
                System.out.println("The winning Elf in a game with " + puzzleInput + " elves is " + Josephus.winner(puzzleInput))
        );

        Timer.run(() -> System.out.println("The winner using opposite strategy is " + part2(puzzleInput)));
    }

    public static int part2(int teamSize) {
        final var left = new LinkedList<Integer>();
        final var right = new LinkedList<Integer>();

        for (var i = 1; i < teamSize + 1; i++) {
            if (i < teamSize / 2 + 1)
                left.offerLast(i);
            else
                right.offerFirst(i);
        }

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.size() > right.size())
                left.pollLast();
            else
                right.pollLast();

            right.offerFirst(left.pollFirst());
            left.offerLast(right.pollLast());
        }

        return left.isEmpty() ? right.pop() : left.pop();
    }
}
