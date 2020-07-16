package com.putoet.day21;

import com.putoet.utils.ResourceLines;

public class Day21 {
    public static void main(String[] args) {
        final PasswordScrambler scrambler = PasswordScrambler.of(ResourceLines.list("/day21.txt"));
        final String puzzleInput = "abcdefgh";

        System.out.println("Scrambled password is '" + scrambler.apply(puzzleInput) + "'");
    }
}
