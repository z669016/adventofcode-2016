package com.putoet.day17;

import java.util.*;

class Finder<T> {
    public interface State<T> {
        boolean isFinished();
        List<? extends State<T>> next();
        T solution();
    }

    public List<T> solve(final State<T> initialState) {
        final var queue = new LinkedList<State<T>>();
        final var solutions = new ArrayList<T>();

        queue.offer(initialState);
        while (!queue.isEmpty()) {
            final State<T> state = queue.poll();

            if (state.isFinished())
                solutions.add(state.solution());
            else
                state.next().forEach(queue::offer);
        }
        return solutions;
    }
}
