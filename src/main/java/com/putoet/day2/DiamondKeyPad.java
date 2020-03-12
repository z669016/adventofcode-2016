package com.putoet.day2;

import com.putoet.utils.FixedNonNegativeGrid;

import java.util.List;

public class DiamondKeyPad extends AbstractKeyPadBase<String> {
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
        switch (currentY) {
            case 0:
            case 4:
                return 2;
            case 1:
            case 3:
                return 1;
            default:
                return 0;
        }
    }

    private static int maxXforY(int currentY) {
        switch (currentY) {
            case 0:
            case 4:
                return 2;
            case 1:
            case 3:
                return 3;
            default:
                return 4;
        }
    }

    private static int minYforX(int currentX) {
        switch (currentX) {
            case 2:
                return 0;
            case 1:
            case 3:
                return 1;
            default:
                return 2;
        }
    }

    private static int maxYforX(int currentX) {
        switch (currentX) {
            case 2:
                return 4;
            case 1:
            case 3:
                return 3;
            default:
                return 2;
        }
    }

    @Override
    public void press() {
        code.add(grid.get(currentX, currentY));
    }
}
