package com.putoet.day8;

import com.putoet.utils.FixedGrid;

public class TinyCodeDisplayingScreen {
    public void display(FixedGrid<Integer> grid) {
        for (int idy = 0; idy < grid.height(); idy++) {
            for (int idx = 0; idx < grid.width(); idx++)
                System.out.print(grid.get(idx, idy) == 0 ? "." : "#");
            System.out.println();
        }
    }
}
