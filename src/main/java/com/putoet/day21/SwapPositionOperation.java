package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

public class SwapPositionOperation implements ScrambleOperation {
    private final int from, to;

    public SwapPositionOperation(@NotNull String line) {
        this(line.split(" "));
    }

    public SwapPositionOperation(@NotNull String[] split) {
        assert split.length == 6;
        assert "swap".equals(split[0]);
        assert "position".equals(split[1]);
        assert "with".equals(split[3]);
        assert split[1].equals(split[4]);

        final var f = Integer.parseInt(split[2]);
        final var t = Integer.parseInt(split[5]);

        from = Math.min(f, t);
        to = Math.max(f, t);

        assert from >= 0;
    }

    @Override
    public String apply(@NotNull String password) {
        return swap(password, from, to);
    }

    @Override
    public String unApply(@NotNull String password) {
        return apply(password);
    }

    @Override
    public String toString() {
        return String.format("swap position %d with %d", from, to);
    }

    private static String swap(@NotNull String password, int from, int to) {
        if (from >= password.length())
            throw new IllegalArgumentException("Cannot swap from " + from + " for password '" + password + "'");
        if (to >= password.length())
            throw new IllegalArgumentException("Cannot swap to " + to + " for password '" + password + "'");

        if (from == to)
            return password;

        final var fromChar = password.charAt(from);
        final var toChar = password.charAt(to);

        return password.substring(0, from) + toChar + password.substring(from + 1, to) + fromChar + password.substring(to + 1);
    }
}
