package com.putoet.day11;

import java.util.Objects;

record BuildingState(int steps, Floor floor, BuildingState parent) implements Comparable<BuildingState> {
    public BuildingState {
        assert steps >= 0;
        assert floor != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor);
    }

    @Override
    public int compareTo(BuildingState o) {
        return Integer.compare(steps, o.steps);
    }
}
