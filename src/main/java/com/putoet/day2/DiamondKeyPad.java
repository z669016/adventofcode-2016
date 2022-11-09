package com.putoet.day2;

import com.putoet.utils.FixedNonNegativeGrid;

import java.util.List;

public class DiamondKeyPad extends AbstractKeyPad<String> {
    private final FixedNonNegativeGrid<String> grid;

    public DiamondKeyPad() {
        this.grid = new FixedNonNegativeGrid<>(List.of(
                List.of("", "", "D", "", ""),
                List.of("", "A", "B", "C", ""),
                List.of("5", "6", "7", "8", "9"),
                List.of("", "2", "3", "4", ""),
                List.of("", "", "1", "", "")
        ));

        this.currentX = 0;
        this.currentY = 2;
    }

    @Override
    public void move(SquareKeyPad.Direction direction) {
        final int minY = minYforX(currentX);
        final int maxY = maxYforX(currentX);
        final int minX = minXforY(currentY);
        final int maxX = maxXforY(currentY);

        switch (direction) {
            case U: if (currentY < maxY) currentY++; break;
            case D: if (currentY > minY) currentY--; break;
            case L: if (currentX > minX) currentX--; break;
            case R: if (currentX < maxX) currentX++; break;
        }
    }

    private static int minXforY(int currentY) {
        return switch (currentY) {
            case 0, 4 -> 2;
            case 1, 3 -> 1;
            default -> 0;
        };
    }

    private static int maxXforY(int currentY) {
        return switch (currentY) {
            case 0, 4 -> 2;
            case 1, 3 -> 3;
            default -> 4;
        };
    }

    private static int minYforX(int currentX) {
        return switch (currentX) {
            case 2 -> 0;
            case 1, 3 -> 1;
            default -> 2;
        };
    }

    private static int maxYforX(int currentX) {
        return switch (currentX) {
            case 2 -> 4;
            case 1, 3 -> 3;
            default -> 2;
        };
    }

    @Override
    public void press() {
        code.add(grid.get(currentX, currentY));
    }
}
