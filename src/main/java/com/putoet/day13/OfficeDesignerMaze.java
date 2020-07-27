package com.putoet.day13;

import com.putoet.utils.maze.AbstractMaze;

public class OfficeDesignerMaze extends AbstractMaze<String> {
    private final int officeDesignerFavouriteNumber;

    public OfficeDesignerMaze(int officeDesignerFavouriteNumber) {
        this.officeDesignerFavouriteNumber = officeDesignerFavouriteNumber;
    }

    @Override
    public boolean contains(int x, int y) {
        return y >= 0 && x >= 0;
    }

    @Override
    public String cell(int x, int y) {
        return isWall(x, y) ? "#" : ".";
    }

    @Override
    public boolean isWall(int x, int y) {
        final int code = (x*x) + (3*x) + (2*x*y) + y + (y*y) + officeDesignerFavouriteNumber;
        final String binary = Integer.toBinaryString(code);
        final long ones = binary.chars()
                .filter(bit -> bit == '1')
                .count();
        return ones % 2 == 1;
    }

    @Override
    public void draw() {
        draw(10, 10);
    }
}
