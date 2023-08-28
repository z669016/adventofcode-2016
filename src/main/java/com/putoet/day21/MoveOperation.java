package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

class MoveOperation implements ScrambleOperation {
    private final int from, to;

    public MoveOperation(@NotNull String line) {
        this(line.split(" "));
    }

    public MoveOperation(@NotNull String[] split) {
        assert split.length == 6;
        assert "move".equals(split[0]);
        assert "position".equals(split[1]);
        assert "to".equals(split[3]);
        assert split[1].equals(split[4]);

        from = Integer.parseInt(split[2]);
        to = Integer.parseInt(split[5]);

        assert from >= 0;
        assert to >= 0;
    }

    @Override
    public String apply(@NotNull String password) {
        return move(password,from, to);
    }

    @Override
    public String unApply(@NotNull String password) {
        return move(password, to, from);
    }

    @Override
    public String toString() {
        return String.format("move position %d to %d", from, to);
    }

    private static String move(@NotNull String password, int from, int to) {
        if (from >= password.length())
            throw new IllegalArgumentException("Cannot move from " + from + " for password '" + password + "'");
        if (to >= password.length())
            throw new IllegalArgumentException("Cannot move to " + to + " for password '" + password + "'");

        if (from == to)
            return password;

        return putIn(takeFrom(password, from), password.charAt(from), to);
    }

    private static String putIn(String password, char c, int to) {
        return password.substring(0, to) + c + password.substring(to);
    }

    private static String takeFrom(String password, int from) {
        return password.substring(0, from) + password.substring(from + 1);
    }
}
