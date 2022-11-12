package com.putoet.day8;

import com.putoet.utils.FixedGrid;
import com.putoet.utils.FixedNonNegativeGrid;

import java.util.Optional;
import java.util.function.Consumer;

public class DoorLock {
    private final CardReader cardReader;
    private final FixedGrid<Integer> grid;

    public DoorLock(CardReader cardReader) {
        this.cardReader = cardReader;
        this.grid = new FixedNonNegativeGrid<>(6, 50, 0);
    }

    public void swipe() {
        Optional<Consumer<FixedGrid<Integer>>> consumer = cardReader.get();
        while (consumer.isPresent()) {
            consumer.get().accept(grid);
            consumer = cardReader.get();
        }
    }

    public int pixelsLit() {
        int pixelsLit = 0;
        for (int idy = 0; idy < grid.height(); idy++)
            for (int idx = 0; idx < grid.width(); idx++)
                pixelsLit += grid.get(idx, idy);

        return pixelsLit;
    }

    public void display() {
        display(grid);
    }

    public static void display(FixedGrid<Integer> grid) {
        for (int idy = 0; idy < grid.height(); idy++) {
            for (int idx = 0; idx < grid.width(); idx++)
                System.out.print(grid.get(idx, idy) == 0 ? "." : "#");
            System.out.println();
        }
    }
}
