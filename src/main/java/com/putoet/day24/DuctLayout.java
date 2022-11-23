package com.putoet.day24;

import com.putoet.maze.Maze;
import com.putoet.misc.TSP;
import com.putoet.search.GenericSearch;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.function.Predicate;

import static com.putoet.search.GenericSearch.bfs;

public class DuctLayout implements Maze<Cell> {
    private final int rows, columns;
    final private Cell[][] grid;

    public DuctLayout(List<String> maze) {
        assert maze != null;
        assert maze.size() > 0;

        grid = maze.stream()
                .map(line -> line.chars()
                        .mapToObj(Cell::of)
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new);

        this.rows = grid.length;
        this.columns = grid[0].length;

        Arrays.stream(grid).forEach(row -> {
            if (row.length != this.columns)
                throw new IllegalArgumentException("Rows have variable lengths, which is not allowed");
        });
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                sb.append(cell.toString());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public List<Maze.Location> successors(Maze.Location ml) {
        assert ml != null;

        final List<Maze.Location> locations = new ArrayList<>();
        if (ml.row + 1 < rows && grid[ml.row + 1][ml.column] != Cell.WALL) {
            locations.add(new Maze.Location(ml.row + 1, ml.column));
        }
        if (ml.row - 1 >= 0 && grid[ml.row - 1][ml.column] != Cell.WALL) {
            locations.add(new Maze.Location(ml.row - 1, ml.column));
        }
        if (ml.column + 1 < columns && grid[ml.row][ml.column + 1] != Cell.WALL) {
            locations.add(new Maze.Location(ml.row, ml.column + 1));
        }
        if (ml.column - 1 >= 0 && grid[ml.row][ml.column - 1] != Cell.WALL) {
            locations.add(new Maze.Location(ml.row, ml.column - 1));
        }
        return locations;
    }

    @Override
    public Cell cell(Location location) {
        Maze.checkLocation(location, rows, columns);
        return grid[location.row][location.column];
    }

    @Override
    public Optional<Location> locate(Predicate<Cell> predicate) {
        return locate(grid, predicate);
    }

    public List<Cell> gates() {
        return Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .filter(Cell::isGate)
                .sorted()
                .toList();
    }

    public List<List<Cell>> gateCombinations() {
        return Generator.combination(gates())
                .simple(2)
                .stream()
                .toList();
    }

    public Map<String, Map<String,Integer>> distances(List<List<Cell>> combinations) {
        assert combinations != null;

        final Map<String, Map<String,Integer>> distances = new HashMap<>();
        gates().forEach(gate -> distances.put(gate.toString(),new HashMap<>()));

        combinations.forEach(combination-> {
            final Cell u = combination.get(0);
            final Cell v = combination.get(1);

            final Optional<Maze.Location> start = locate(cell -> cell == u);

            start.ifPresent(location -> {
                final Optional<GenericSearch.Node<Maze.Location>> weight =
                        bfs(location, ml -> cell(ml) == v, this::successors);
                weight.ifPresent(locationNode -> {
                    distances.get(u.toString()).put(v.toString(), locationNode.steps());
                    distances.get(v.toString()).put(u.toString(), locationNode.steps());
                });
            });
        });

        return distances;
    }

    public OptionalInt shortestPathFrom(Cell start, Map<String, Map<String,Integer>> distances) {
        assert start != null;
        assert distances != null;

        final List<Cell[]> permutations = TSP.permutations(gates().toArray(Cell[]::new)).stream()
                .filter(g -> g[0] == start)
                .toList();

        return permutations.stream().mapToInt(path -> {
            int length = 0;
            for (int idx = 0; idx < path.length - 1; idx++) {
                length += distances.get(path[idx].toString()).get(path[idx+1].toString());
            }
            return length;
        }).min();
    }
}
