package com.putoet.day11;

import java.util.*;

public class Solver {
    public static int solve(Floor[] floors) {
        final Queue<Building> queue = new PriorityQueue<>();
        final Set<Building> history = new HashSet<>();
        final Building init = new Building(0, 0, floors, null);

        queue.offer(init);
        history.add(init);

        Building found = null;
        while (found == null && !queue.isEmpty()) {
            final Building building = queue.poll();
            if (building.done()) {
                found = building;
            } else {
                final List<Building> next = building.nextOptions();
                next.stream().filter(history::add).forEach(queue::offer);
            }
        }

        if (found == null)
            throw new IllegalArgumentException("Could not solve this puzzle.");
        else
            System.out.println(found);


        return found.count();
    }
}
