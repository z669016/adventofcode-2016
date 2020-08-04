package com.putoet.day24;

import com.putoet.misc.TSP;
import com.putoet.utils.ResourceLines;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import static com.putoet.day24.Cell.GATE0;

public class Day24 {
    public static void main(String[] args) {
        final List<String> maze = ResourceLines.list("/day24.txt");
        final DuctLayout layout = new DuctLayout(maze);

        System.out.println("Duct has " + layout.gates().size() + " gates :" + layout.gates());

        final List<List<Cell>> combinations = layout.gateCombinations();
        final Map<String, Map<String, Integer>> distances = layout.distances(combinations);
        final OptionalInt min = layout.shortestPathFrom(GATE0, distances);

        if (min.isEmpty())
            System.out.println("No solution found.");
        else
            System.out.println("Minimum number of steps from gate0 is " + min.getAsInt());

        final TSP tsp = new TSP(distances);
        final String[] shortestPath = tsp.findShortestPath();
        System.out.println("Traveling salesman solution is " + tsp.pathDistance(shortestPath));
    }
}
