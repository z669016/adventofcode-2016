package com.putoet.day2;

import com.putoet.utils.FixedGrid;
import com.putoet.utils.FixedNonNegativeGrid;

import java.util.List;

public class SquareKeyPad extends AbstractKeyPad<Integer> {
    private final FixedGrid<Integer> grid = new FixedNonNegativeGrid<>(List.of(
            List.of(7, 8, 9),
            List.of(4, 5, 6),
            List.of(1, 2, 3)));

    public SquareKeyPad() {
        currentY = currentX = 1;
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case U: if (currentY < (grid.height() - 1)) currentY++; break;
            case D: if (currentY > 0) currentY--; break;
            case L: if (currentX > 0) currentX--; break;
            case R: if (currentX < (grid.width() - 1)) currentX++; break;
        }
    }

    @Override
    public void press() {
        code.add(grid.get(currentX, currentY));
    }
}
