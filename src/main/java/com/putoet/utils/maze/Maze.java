package com.putoet.utils.maze;

import com.putoet.utils.maze.Route;

import java.util.Optional;

public interface Maze {
    boolean isWall(int x, int y);

    boolean isOpen(int x, int y);

    void draw(Route route);

    void draw(int maxX, int maxY);
}
