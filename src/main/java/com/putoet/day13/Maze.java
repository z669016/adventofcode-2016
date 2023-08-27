package com.putoet.day13;

record Maze(int officeDesignerFavouriteNumber) {
    public boolean isWall(int x, int y) {
        final var code = (x*x) + (3*x) + (2*x*y) + y + (y*y) + officeDesignerFavouriteNumber;
        final var binary = Integer.toBinaryString(code);
        final var ones = binary.chars()
                .filter(bit -> bit == '1')
                .count();
        return ones % 2 == 1;
    }
}
