package com.putoet.day18;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Room {
    private final Tile[][] tiles;

    private Room(Tile[][] tiles) {
        assert tiles != null;

        this.tiles = tiles;
    }

    public static Room of(String rowOfTiles, int numberOfRows) {
        assert rowOfTiles != null;
        assert rowOfTiles.matches("^[\\^\\.]+$");
        assert numberOfRows > 0;

        final Tile[][] tiles = new Tile[numberOfRows][];
        tiles[0] = Tile.of(rowOfTiles);
        for (int idx = 1; idx < numberOfRows; idx++) {
            tiles[idx] = next(tiles[idx - 1]);
        }

        return new Room(tiles);
    }

    private static Tile[] next(Tile[] rowOfTiles) {
        final Tile[] nextRowOfTiles = new Tile[rowOfTiles.length];

        for (int idx = 0; idx < rowOfTiles.length; idx++) {
            nextRowOfTiles[idx] = next(idx == 0 ? null : rowOfTiles[idx-1]
                    , rowOfTiles[idx]
                    , idx == rowOfTiles.length - 1 ? null : rowOfTiles[idx + 1]
                    );
        }

        return nextRowOfTiles;
    }

    private static Tile next(Tile left, Tile center, Tile right) {
        left = (left == null ? Tile.SAFE : left);
        right = (right == null ? Tile.SAFE : right);

        if (left.isTrap() && center.isTrap() && right.isSafe())
            return Tile.TRAP;

        if (left.isTrap() && center.isSafe() && right.isSafe())
            return Tile.TRAP;

        if (left.isSafe() && center.isTrap() && right.isTrap())
            return Tile.TRAP;

        if (left.isSafe() && center.isSafe() && right.isTrap())
            return Tile.TRAP;

        return Tile.SAFE;
    }

    public long safeTileCount() {
        return Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(Tile::isSafe)
                .count();
    }

    @Override
    public String toString() {
        return Arrays.stream(tiles)
                .map(tiles -> Arrays.stream(tiles)
                        .map(Tile::toString).collect(Collectors.joining())
                )
                .collect(Collectors.joining("\n")) ;
    }
}
