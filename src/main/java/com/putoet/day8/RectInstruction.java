package com.putoet.day8;

import com.putoet.utils.FixedGrid;

import java.util.function.Consumer;

class RectInstruction implements Consumer<FixedGrid<Integer>> {
    private final int a;
    private final int b;

    public RectInstruction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void accept(FixedGrid<Integer> grid) {
        for (var idy = 0; idy < b; idy++)
            for (var idx = 0; idx < a; idx++)
                grid.set(idx, idy, 1);
    }
}
