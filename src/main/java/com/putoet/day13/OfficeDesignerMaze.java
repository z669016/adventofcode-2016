package com.putoet.day13;

import com.putoet.utils.maze.Maze;
import com.putoet.utils.maze.Route;

import java.util.Optional;

public class OfficeDesignerMaze implements Maze {
    private final int officeDesignerFavouriteNumber;

    public OfficeDesignerMaze(int officeDesignerFavouriteNumber) {
        this.officeDesignerFavouriteNumber = officeDesignerFavouriteNumber;
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
    public boolean isOpen(int x, int y) {
        return !isWall(x, y);
    }

    @Override
    public void draw(Route route) {
        final int maxX = route.maxX() + 1, maxY = route.maxY() + 1;
        draw(Optional.of(route), maxX, maxY);
    }

    @Override
    public void draw(int maxX, int maxY) {
        draw(Optional.empty(), maxX, maxY);
    }

    public void draw(Optional<Route> route, int maxX, int maxY) {
        System.out.print("  ");
        for (int x = 0; x < maxX; x++)
            System.out.print(x%10);
        System.out.println();

        for (int y = 0; y < maxY; y++) {
            System.out.print(y%10);
            System.out.print(" ");
            for (int x = 0; x < maxX; x++) {
                final boolean onRoute = route.isPresent() && route.get().contains(x, y);
                if (onRoute)
                    System.out.print("O");
                else
                    System.out.print(isWall(x, y) ? "#" : ".");
            }
            System.out.println();
        }
    }
}
