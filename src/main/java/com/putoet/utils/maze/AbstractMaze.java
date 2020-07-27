package com.putoet.utils.maze;

import java.util.Optional;

public abstract class AbstractMaze implements Maze {
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

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
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
