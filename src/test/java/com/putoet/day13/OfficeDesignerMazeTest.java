package com.putoet.day13;

import com.putoet.utils.maze.Maze;
import com.putoet.utils.maze.Route;
import com.putoet.utils.maze.RouteTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfficeDesignerMazeTest {
    @Test
    void isWall() {
        final int officeDesignerFavouriteNumber = 1352;
        final Maze maze = new OfficeDesignerMaze(officeDesignerFavouriteNumber);

        assertFalse(maze.isWall(31,39));
    }

    @Test
    void draw() {
        final int officeDesignerFavouriteNumber = 10;
        final Maze maze = new OfficeDesignerMaze(officeDesignerFavouriteNumber);
        final Route route = Route.of(RouteTest.POINTS);

        maze.draw(route);
    }
}