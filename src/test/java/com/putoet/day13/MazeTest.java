package com.putoet.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    @Test
    void isWall() {
        final int officeDesignerFavouriteNumber = 1352;
        final Maze maze = new Maze(officeDesignerFavouriteNumber);

        assertFalse(maze.isWall(31,39));
    }

    @Test
    void draw() {
        final int officeDesignerFavouriteNumber = 10;
        final Maze maze = new Maze(officeDesignerFavouriteNumber);
        final Route route = Route.of(RouteTest.POINTS);

        maze.draw(route);
    }
}