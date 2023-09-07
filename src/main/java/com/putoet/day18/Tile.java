package com.putoet.day18;

record Tile(boolean safe) {
    public static final Tile SAFE = new Tile('.');
    public static final Tile TRAP = new Tile('^');

    private Tile(int c) {
        this(c == '.');
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
