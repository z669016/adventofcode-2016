package com.putoet.day19;

import com.putoet.math.Josephus;

import java.util.*;

public class Day19 {
    public static void main(String[] args) {
        final int puzzleInput = 3004953;
        System.out.println("The winning Elf in a game with " + puzzleInput + " elves is " + Josephus.winner(puzzleInput));
        System.out.println("The winner using opposite strategy is " + part2(puzzleInput));
    }

    public static int part2(int teamSize) {
        final Deque<Integer> left = new LinkedList<>();
        final Deque<Integer> right = new LinkedList<>();

        for (int i = 1; i < teamSize + 1; i++) {
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
