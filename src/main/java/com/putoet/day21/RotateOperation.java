package com.putoet.day21;

public class RotateOperation implements ScrambleOperation {
    private final int steps;
    private boolean left;

    public RotateOperation(String line) {
        this(line != null ? line.split(" ") : new String[] {});
    }

    public RotateOperation(String[] split) {
        assert split != null;
        assert split.length == 4;
        assert "rotate".equals(split[0]);
        assert "left|right".contains(split[1]);
        assert "steps|steps".contains(split[3]);

        left = "left".equals(split[1]);
        steps = Integer.parseInt(split[2]);

        assert steps >= 0;
    }

    @Override
    public String apply(String password) {
        assert password != null;

        if (steps == 0 || password.length() == 1)
            return password;

        return left ? rotateLeft(password, steps) : rotateRight(password, steps);
    }

    public static String rotateRight(String password, int steps) {
        for (int idx = 0; idx < steps; idx++)
            password = password.charAt(password.length()-1) + password.substring(0, password.length() - 1);
        return password;
    }

    public static String rotateLeft(String password, int steps) {
        for (int idx = 0; idx < steps; idx++)
            password = password.substring(1) + password.charAt(0);
        return password;
    }
}
