package com.putoet.utils;

import java.util.*;

public class BacktrackProblemSolver<T> {
    public interface BacktrackState<T> {
        boolean isFinished();
        List<? extends BacktrackState<T>> next();
        T solution();
    }

    private final Stack<BacktrackState<T>> stateStack = new Stack<>();

    public Optional<T> solve(final BacktrackState<T> initialState) {
        final List<T> solutions = solveAll(initialState, true);
        return solutions.isEmpty() ? Optional.empty() : Optional.of(solutions.get(0));
    }

    public List<T> solveAll(final BacktrackState<T> initialState) {
        return solveAll(initialState, false);
    }

    private List<T> solveAll(final BacktrackState<T> initialState, boolean justOne) {
        final List<T> solutions = new ArrayList<>();

        stateStack.push(initialState);
        while (!stateStack.isEmpty()) {
            final BacktrackState<T> state = stateStack.pop();

            if (state.isFinished())
                solutions.add(state.solution());
            else
                state.next().forEach(stateStack::push);

            if (justOne && solutions.size() == 1) {
                stateStack.removeAllElements();
            }

        }
        return solutions;
    }
}
