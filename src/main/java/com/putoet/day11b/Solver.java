package com.putoet.day11b;

import java.util.*;

public class Solver {
    public static int solve(Floor[] floors) {
        final Queue<Building> queue = new PriorityQueue<>(Comparator.comparingInt(Building::count));
        final Set<Building> history = new HashSet<>();
        final Building init = new Building(0, 0, new Floors(floors), null);

        queue.offer(init);
        Building found = null;
        while (found == null && !queue.isEmpty()) {
            final Building building = queue.poll();
            history.add(building);
            if (building.done()) {
                found = building;
            } else {
                final List<Building> next = building.nextOptions();
                next.stream().filter(b -> !history.contains(b)).forEach(queue::offer);
            }
        }

        if (found == null)
            throw new IllegalArgumentException("Could not solve this puzzle.");
        else
            System.out.println(found);


        return found.count();
    }
}
