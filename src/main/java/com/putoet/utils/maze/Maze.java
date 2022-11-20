package com.putoet.utils.maze;

public interface Maze<T> {
    boolean contains(int x, int y);

    T cell(int x, int y);

    boolean isWall(int x, int y);

    void draw(Route route);
}
