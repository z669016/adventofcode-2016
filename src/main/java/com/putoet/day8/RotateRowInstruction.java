package com.putoet.day8;

import com.putoet.utils.FixedGrid;

import java.util.function.Consumer;

class RotateRowInstruction implements Consumer<FixedGrid<Integer>> {
    private final int a;
    private final int b;

    public RotateRowInstruction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void accept(FixedGrid<Integer> grid) {
        var rotations = b % grid.width();

        for (var pixels = 0; pixels < rotations; pixels++) {
            final var temp = grid.get(grid.width() - 1, a);

            for (var idx = grid.width() - 1; idx > 0; idx--)
                grid.set(idx, a, grid.get(idx - 1, a));

            grid.set(0, a, temp);
        }
    }
}
