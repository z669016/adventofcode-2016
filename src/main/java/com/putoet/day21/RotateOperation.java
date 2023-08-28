package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

class RotateOperation implements ScrambleOperation {
    private final int steps;
    private final boolean left;

    public RotateOperation(@NotNull String line) {
        this(line.split(" "));
    }

    public RotateOperation(@NotNull String[] split) {
        assert split.length == 4;
        assert "rotate".equals(split[0]);
        assert "left|right".contains(split[1]);
        assert "steps|steps".contains(split[3]);

        left = "left".equals(split[1]);
        steps = Integer.parseInt(split[2]);

        assert steps >= 0;
    }

    @Override
    public String apply(@NotNull String password) {
        return rotate(password, steps, left);
    }

    @Override
    public String unApply(@NotNull String password) {
        return rotate(password, steps, !left);
    }

    @Override
    public String toString() {
        return String.format("rotate %s %d %s", left ? "left" : "right", steps, steps == 1 ? "step" : "steps");
    }

    private static String rotate(@NotNull String password, int steps, boolean left) {
        if (steps == 0 || password.length() == 1)
            return password;

        return left ? rotateLeft(password, steps) : rotateRight(password, steps);
    }

    public static String rotateRight(@NotNull String password, int steps) {
        for (var idx = 0; idx < steps; idx++)
            password = password.charAt(password.length()-1) + password.substring(0, password.length() - 1);
        return password;
    }

    public static String rotateLeft(@NotNull String password, int steps) {
        for (var idx = 0; idx < steps; idx++)
            password = password.substring(1) + password.charAt(0);
        return password;
    }
}
