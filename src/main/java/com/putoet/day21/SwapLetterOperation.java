package com.putoet.day21;

public class SwapLetterOperation implements ScrambleOperation {
    private final String from, to;

    public SwapLetterOperation(String line) {
        this(line != null ? line.split(" ") : new String[] {});
    }

    public SwapLetterOperation(String[] split) {
        assert split != null;
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
    public String apply(String password) {
        return swap(password, from, to);
    }

    @Override
    public String unApply(String password) {
        return apply(password);
    }

    @Override
    public String toString() {
        return String.format("swap letter %s with %s", from, to);
    }

    private static String swap(String password, String from, String to) {
        assert password != null;

        if (!password.contains(from) || !password.contains(to))
            throw new IllegalArgumentException("Cannot swap '" + from + "' and '" + to + "' for password '" + password +"'");

        if (password.contains(from) && password.contains(to))
            return password.replace(from, "$").replace(to, from).replace("$", to);

        return password;
    }
}
