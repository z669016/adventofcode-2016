package com.putoet.day8;

import com.putoet.utils.FixedGrid;

import java.util.function.Consumer;

public class RotateRowInstruction implements Consumer<FixedGrid<Integer>> {
    private final int a;
    private final int b;

    public RotateRowInstruction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void accept(FixedGrid<Integer> grid) {
        int rotations = b % grid.width();

        for (int pixels = 0; pixels < rotations; pixels++) {
            final int temp = grid.get(grid.width() - 1, a);

            for (int idx = grid.width() - 1; idx > 0; idx--)
                grid.set(idx, a, grid.get(idx - 1, a));

            grid.set(0, a, temp);
        }
    }
}
