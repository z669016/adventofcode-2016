package com.putoet.day18;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Room {
    private final Tile[][] tiles;

    private Room(Tile[][] tiles) {
        assert tiles != null;

        this.tiles = tiles;
    }

    public static Room of(String rowOfTiles, int numberOfRows) {
        assert numberOfRows > 0;

        final TileRowSupplier supplier = new TileRowSupplier(Tile.of(rowOfTiles));
        return new Room(IntStream.range(0, numberOfRows)
                .mapToObj(idx -> supplier.get())
                .toArray(Tile[][]::new)
        );
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

    private static class TileRowSupplier implements Supplier<Tile[]> {
        private Tile[] nextRow;

        public TileRowSupplier(Tile[] nextRow) {
            assert nextRow != null;

            this.nextRow = nextRow;
        }

        @Override
        public Tile[] get() {
            final Tile[] result = nextRow;
            nextRow = next();
            return result;
        }

        private Tile[] next() {
            final Tile[] nextRowOfTiles = new Tile[nextRow.length];

            for (int idx = 0; idx < nextRow.length; idx++) {
                nextRowOfTiles[idx] = next(idx == 0 ? null : nextRow[idx-1]
                        , nextRow[idx]
                        , idx == nextRow.length - 1 ? null : nextRow[idx + 1]
                );
            }

            return nextRowOfTiles;
        }

        private Tile next(Tile left, Tile center, Tile right) {
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
    }
}
