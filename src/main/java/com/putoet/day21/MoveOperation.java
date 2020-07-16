package com.putoet.day21;

public class MoveOperation implements ScrambleOperation {
    private final int from, to;

    public MoveOperation(String line) {
        this(line != null ? line.split(" ") : new String[] {});
    }

    public MoveOperation(String[] split) {
        assert split != null;
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
    public String apply(String password) {
        assert password != null;

        if (from >= password.length())
            throw new IllegalArgumentException("Cannot move from " + from + " for password '" + password + "'");
        if (to >= password.length())
            throw new IllegalArgumentException("Cannot move to " + to + " for password '" + password + "'");

        if (from == to)
            return password;

        return putIn(takeFrom(password), password.charAt(from));
    }

    private String putIn(String password, char c) {
        return password.substring(0, to) + c + password.substring(to);
    }

    private String takeFrom(String password) {
        return password.substring(0, from) + password.substring(from + 1);
    }
}
