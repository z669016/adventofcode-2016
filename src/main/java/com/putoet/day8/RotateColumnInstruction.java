package com.putoet.day8;

import com.putoet.utils.FixedGrid;

import java.util.function.Consumer;

class RotateColumnInstruction  implements Consumer<FixedGrid<Integer>> {
    private final int a;
    private final int b;

    public RotateColumnInstruction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void accept(FixedGrid<Integer> grid) {
        var rotations = b % grid.height();
        for (var pixels = 0; pixels < rotations; pixels++) {
            final var temp = grid.get(a, grid.height() - 1);

            for (var idy = grid.height() - 1; idy > 0; idy--)
                grid.set(a, idy, grid.get(a, idy - 1));

            grid.set(a, 0, temp);
        }
    }
}
