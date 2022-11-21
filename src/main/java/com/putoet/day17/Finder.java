package com.putoet.day17;

import java.util.*;

public class Finder<T> {
    public interface State<T> {
        boolean isFinished();
        List<? extends State<T>> next();
        T solution();
    }

    public List<T> solve(final State<T> initialState) {
        final Queue<State<T>> queue = new LinkedList<>();
        final List<T> solutions = new ArrayList<>();

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
