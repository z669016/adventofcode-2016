package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

public class SwapLetterOperation implements ScrambleOperation {
    private final String from, to;

    public SwapLetterOperation(@NotNull String line) {
        this(line.split(" "));
    }

    public SwapLetterOperation(@NotNull String[] split) {
        assert split.length == 6;
        assert "swap".equals(split[0]);
        assert "letter".equals(split[1]);
        assert "with".equals(split[3]);
        assert split[1].equals(split[4]);

        from = split[2];
        to = split[5];

        assert from.length() == 1 && from.matches("[a-z]");
        assert to.length() == 1 && to.matches("[a-z]");
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
        return String.format("swap letter %s with %s", from, to);
    }

    private static String swap(@NotNull String password, String from, String to) {
        if (!password.contains(from) || !password.contains(to))
            throw new IllegalArgumentException("Cannot swap '" + from + "' and '" + to + "' for password '" + password +"'");

        if (password.contains(from) && password.contains(to))
            return password.replace(from, "$").replace(to, from).replace("$", to);

        return password;
    }
}
