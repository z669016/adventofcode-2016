package com.putoet.day21;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day21 {
    public static void main(String[] args) {
        final var scrambler = PasswordScrambler.of(ResourceLines.list("/day21.txt"));

        Timer.run(() ->
                System.out.println("Scrambled password is '" + scrambler.apply("abcdefgh") + "'")
        );

        Timer.run(() -> {
            final var unscrambled = scrambler.unApply("fbgdceah");
            System.out.println("Unscrambled password is '" + unscrambled + "'");
            System.out.println("Check ... re-scrambled password is '" + scrambler.apply(unscrambled) + "'");
        });
    }
}
