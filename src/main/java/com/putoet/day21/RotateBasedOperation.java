package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

public class RotateBasedOperation implements ScrambleOperation {
    private final String letter;

    public RotateBasedOperation(@NotNull String line) {
        this(line.split(" "));
    }

    public RotateBasedOperation(@NotNull String[] split) {
        assert split.length == 7;
        assert "rotate".equals(split[0]);
        assert "based".contains(split[1]);
        assert "on".contains(split[2]);
        assert "position".contains(split[3]);
        assert "of".contains(split[4]);
        assert "letter".equals(split[5]);
        assert split[6].matches("[a-z]");

        letter = split[6];
    }

    @Override
    public String apply(@NotNull String password) {
        return RotateOperation.rotateRight(password, steps(password, letter));
    }

    @Override
    public String unApply(@NotNull String password) {
        for (var steps = 1; steps < password.length() + 2; steps++) {
            final var unapplied = RotateOperation.rotateLeft(password, steps);
            if (password.equals(apply(unapplied)))
                return unapplied;
        }

       throw new IllegalStateException("Could not unApply rotate based on positio of letter " + letter + " on password '" + password + "'");
    }

    @Override
    public String toString() {
        return String.format("rotate based on position of letter %s", letter);
    }

    private static int steps(String password, String letter) {
        var steps = password.indexOf(letter); // plus a number of times equal to that index
        if (steps >= 4) steps++; // plus one additional time if the index was at least 4
        steps += 1; // rotate the string to the right one time
        return steps;
    }
}
