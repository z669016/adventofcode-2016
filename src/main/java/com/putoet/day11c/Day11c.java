package com.putoet.day11c;

import java.util.List;

public class Day11c {
    public static void main(String[] args) {
        System.out.println(calculateMOves(List.of(2, 1, 1))); // Fails the test input
        System.out.println(calculateMOves(List.of(4, 5, 1))); // works on my input part 1
        System.out.println(calculateMOves(List.of(8, 5, 1))); // works on my input part 2
    }

    public static int  calculateMOves(List<Integer> list) {
        int totalNumber = 0;
        int moves = 0;

        for (int numberOfItems : list) {
            totalNumber += numberOfItems;
            moves += 2 * (totalNumber - 1) - 1;
        }

        return moves;
    }
}
