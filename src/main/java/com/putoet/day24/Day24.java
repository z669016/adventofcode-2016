package com.putoet.day24;

import com.putoet.misc.TSP;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import static com.putoet.day24.Cell.GATE0;

public class Day24 {
    public static void main(String[] args) {
        final var layout = new DuctLayout(ResourceLines.list("/day24.txt"));
        System.out.println("Duct has " + layout.gates().size() + " gates :" + layout.gates());

        final var combinations = layout.gateCombinations();
        final var distances = layout.distances(combinations);

        Timer.run(() -> {
            final var min = layout.shortestPathFrom(GATE0, distances).orElseThrow();
            System.out.println("Minimum number of steps from gate0 is " + min);
        });

        Timer.run(() -> {
            final var tsp = new TSP(distances);
            final var shortestPath = tsp.findShortestPath();
            System.out.println("Traveling salesman solution is " + tsp.pathDistance(shortestPath));
        });
    }
}
