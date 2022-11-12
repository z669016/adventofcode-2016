package com.putoet.day8;

import com.putoet.utils.FixedGrid;

import java.util.function.Consumer;

public class RectInstruction implements Consumer<FixedGrid<Integer>> {
    private final int a;
    private final int b;

    public RectInstruction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void accept(FixedGrid<Integer> grid) {
        for (int idy = 0; idy < b; idy++)
            for (int idx = 0; idx < a; idx++)
                grid.set(idx, idy, 1);
    }
}
