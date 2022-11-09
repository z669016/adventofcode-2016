package com.putoet.day21;

import com.putoet.resources.ResourceLines;

public class Day21 {
    public static void main(String[] args) {
        final PasswordScrambler scrambler = PasswordScrambler.of(ResourceLines.list("/day21.txt"));

        System.out.println("Scrambled password is '" + scrambler.apply("abcdefgh") + "'");

        final String unscrambled = scrambler.unApply("fbgdceah");
        System.out.println("Unscrambled password is '" + unscrambled + "'");
        System.out.println("Rescrambled password is '" + scrambler.apply(unscrambled) + "'");


        // edcgafbh
        // cegahdbf
    }
}
