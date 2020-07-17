package com.putoet.day21;

public class ReverseOperation implements ScrambleOperation {
    private final int from, to;

    public ReverseOperation(String line) {
        this(line != null ? line.split(" ") : new String[] {});
    }

    public ReverseOperation(String[] split) {
        assert split != null;
        assert split.length == 5;
        assert "reverse".equals(split[0]);
        assert "positions".equals(split[1]);
        assert "through".equals(split[3]);

        final int f = Integer.parseInt(split[2]), t = Integer.parseInt(split[4]);
        from = Math.min(f, t);
        to = Math.max(f, t);

        assert from >= 0;
    }

    @Override
    public String apply(String password) {
        return reverse(password, from, to);
    }

    @Override
    public String unApply(String password) {
        return apply(password);
    }

    @Override
    public String toString() {
        return String.format("reverse position %d through %d", from, to);
    }

    private static String reverse(String password, int from, int to) {
        assert password != null;

        if (from >= password.length() || to >= password.length())
            throw new IllegalArgumentException("Cannot reverse positions " + from + " through " + to + " for password '" + password + "'");

        return password.substring(0, from)
                + new StringBuilder(password.substring(from, to + 1)).reverse().toString()
                + password.substring(to + 1);
    }
}
