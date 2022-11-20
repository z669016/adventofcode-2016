package com.putoet.day11;

import java.util.Objects;

public record State(int steps, Floor floor, State parent) implements Comparable<State> {
    public State {
        assert steps >= 0;
        assert floor != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor);
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(steps, o.steps);
    }
}
