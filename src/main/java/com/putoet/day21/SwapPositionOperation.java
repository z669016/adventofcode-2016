package com.putoet.day21;

public class SwapPositionOperation implements ScrambleOperation {
    private final int from, to;

    public SwapPositionOperation(String line) {
        this(line != null ? line.split(" ") : new String[] {});
    }

    public SwapPositionOperation(String[] split) {
        assert split != null;
        assert split.length == 6;
        assert "swap".equals(split[0]);
        assert "position".equals(split[1]);
        assert "with".equals(split[3]);
        assert split[1].equals(split[4]);

        final int f = Integer.parseInt(split[2]), t = Integer.parseInt(split[5]);
        from = Math.min(f, t);
        to = Math.max(f, t);

        assert from >= 0;
    }

    @Override
    public String apply(String password) {
        return swap(password, from, to);
    }

    @Override
    public String unApply(String password) {
        return apply(password);
    }

    @Override
    public String toString() {
        return String.format("swap position %d with %d", from, to);
    }

    private static String swap(String password, int from, int to) {
        assert password != null;

        if (from >= password.length())
            throw new IllegalArgumentException("Cannot swap from " + from + " for password '" + password + "'");
        if (to >= password.length())
            throw new IllegalArgumentException("Cannot swap to " + to + " for password '" + password + "'");

        if (from == to)
            return password;

        final char fromChar = password.charAt(from);
        final char toChar = password.charAt(to);

        return password.substring(0, from) + toChar + password.substring(from + 1, to) + fromChar + password.substring(to + 1);
    }
}
