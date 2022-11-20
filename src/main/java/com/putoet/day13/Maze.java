package com.putoet.day13;

public record Maze(int officeDesignerFavouriteNumber) {
    public boolean isWall(int x, int y) {
        final int code = (x*x) + (3*x) + (2*x*y) + y + (y*y) + officeDesignerFavouriteNumber;
        final String binary = Integer.toBinaryString(code);
        final long ones = binary.chars()
                .filter(bit -> bit == '1')
                .count();
        return ones % 2 == 1;
    }
}
