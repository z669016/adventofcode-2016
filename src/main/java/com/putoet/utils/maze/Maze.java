package com.putoet.utils.maze;

import com.putoet.utils.maze.Route;

import java.util.Optional;

public interface Maze<T> {
    boolean contains(int x, int y);

    T cell(int x, int y);

    boolean isWall(int x, int y);

    boolean isOpen(int x, int y);

    void draw();

    void draw(Route route);

    void draw(int maxX, int maxY);
}
