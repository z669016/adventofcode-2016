package com.putoet.day21;

public class RotateBasedOperation implements ScrambleOperation {
    private final String letter;

    public RotateBasedOperation(String line) {
        this(line != null ? line.split(" ") : new String[] {});
    }

    public RotateBasedOperation(String[] split) {
        assert split != null;
        assert split.length == 7;
        assert "rotate".equals(split[0]);
        assert "based".contains(split[1]);
        assert "on".contains(split[2]);
        assert "position".contains(split[3]);
        assert "of".contains(split[4]);
        assert "letter".equals(split[5]);
        assert split[6].matches("[a-z]");

        letter = split[6];
    }

    @Override
    public String apply(String password) {
        assert password != null;

        int steps = password.indexOf(letter); // plus a number of times equal to that index
        if (steps >= 4) steps++; // plus one additional time if the index was at least 4
        steps += 1; // rotate the string to the right one time

        return RotateOperation.rotateRight(password, steps);
    }
}
