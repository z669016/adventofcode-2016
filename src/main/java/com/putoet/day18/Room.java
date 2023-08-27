package com.putoet.day18;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Room {
    private final Tile[][] tiles;

    private Room(Tile[][] tiles) {
        assert tiles != null;

        this.tiles = tiles;
    }

    public static Room of(String rowOfTiles, int numberOfRows) {
        assert numberOfRows > 0;

        final var supplier = new TileRowSupplier(Tile.of(rowOfTiles));
        return new Room(IntStream.range(0, numberOfRows)
                .mapToObj(idx -> supplier.get())
                .toArray(Tile[][]::new)
        );
    }

    public long safeTileCount() {
        return Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(Tile::safe)
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
            final var result = nextRow;
            nextRow = next();
            return result;
        }

        private Tile[] next() {
            final var nextRowOfTiles = new Tile[nextRow.length];

            for (var idx = 0; idx < nextRow.length; idx++) {
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

            if (!left.safe() && !center.safe() && right.safe())
                return Tile.TRAP;

            if (!left.safe() && center.safe() && right.safe())
                return Tile.TRAP;

            if (left.safe() && !center.safe() && !right.safe())
                return Tile.TRAP;

            if (left.safe() && center.safe() && !right.safe())
                return Tile.TRAP;

            return Tile.SAFE;
        }
    }
}
