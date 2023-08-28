package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

public class ReverseOperation implements ScrambleOperation {
    private final int from, to;

    public ReverseOperation(@NotNull String line) {
        this(line.split(" "));
    }

    public ReverseOperation(@NotNull String[] split) {
        assert split.length == 5;
        assert "reverse".equals(split[0]);
        assert "positions".equals(split[1]);
        assert "through".equals(split[3]);

        final var f = Integer.parseInt(split[2]);
        final var t = Integer.parseInt(split[4]);

        from = Math.min(f, t);
        to = Math.max(f, t);

        assert from >= 0;
    }

    @Override
    public String apply(@NotNull String password) {
        return reverse(password, from, to);
    }

    @Override
    public String unApply(@NotNull String password) {
        return apply(password);
    }

    @Override
    public String toString() {
        return String.format("reverse position %d through %d", from, to);
    }

    private static String reverse(@NotNull String password, int from, int to) {
        if (from >= password.length() || to >= password.length())
            throw new IllegalArgumentException("Cannot reverse positions " + from + " through " + to + " for password '" + password + "'");

        return password.substring(0, from)
                + new StringBuilder(password.substring(from, to + 1)).reverse()
                + password.substring(to + 1);
    }
}
