package com.putoet.day18;

public class Tile {
    public static Tile SAFE = new Tile('.');
    public static Tile TRAP = new Tile('^');

    private final boolean safe;

    private Tile(int c) {
        assert c == '.' || c == '^';

        this.safe = c == '.';
    }

    public boolean isSafe() {
        return safe;
    }

    public boolean isTrap() {
        return !safe;
    }

    @Override
    public String toString() {
        return safe ? "." : "^";
    }

    public static Tile[] of(String rowOfTiles) {
        assert rowOfTiles != null;
        assert rowOfTiles.matches("^[\\^.]+$");

        return rowOfTiles.chars()
                .mapToObj(c -> c == '.' ? SAFE : TRAP)
                .toArray(Tile[]::new);
    }
}
