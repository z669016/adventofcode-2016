package com.putoet.day24;

import com.putoet.utils.maze.AbstractMaze;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DuctLayout extends AbstractMaze<Cell> {

    private final Cell[][] grid;

    private DuctLayout(Cell[][] grid) {
        this.grid = grid;
    }

    @Override
    public boolean contains(int x, int y) {
        return (y >= 0 && y < grid.length) && (x >= 0 && x < grid[y].length);
    }

    @Override
    public Cell cell(int x, int y) {
        assert y >=0 && y < grid.length;
        assert x >= 0 && x < grid[y].length;

        return grid[y][x];
    }

    @Override
    public boolean isWall(int x, int y) {
        checkCoordinates(x, y);

        return cell(x, y).type() == Cell.Type.WALL;
    }

    @Override
    public boolean isOpen(int x, int y) {
        checkCoordinates(x, y);

        return cell(x, y).type() == Cell.Type.OPEN;
    }

    public boolean isGate(int x, int y) {
        checkCoordinates(x, y);

        return cell(x, y).type() == Cell.Type.GATE;
    }

    @Override
    public void draw() {
        draw(grid[0].length, grid.length);
    }

    public List<Cell> gates() {
        return Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.type() == Cell.Type.GATE)
                .collect(Collectors.toList());
    }

    public static DuctLayout of(List<String> lines) {
        assert lines != null;
        assert lines.size() > 0;

        final Cell[][] grid = lines.stream().sequential()
                .map(line -> line.chars()
                        .mapToObj(Cell::of)
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new);
        return new DuctLayout(grid);
    }
}
