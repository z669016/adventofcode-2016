package com.putoet.utils.maze;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class GenericSearch {

    public static <T extends Comparable<T>> boolean linearContains(List<T> list, T key) {
        for (T item : list) {
            if (item.compareTo(key) == 0) {
                return true; // found a match
            }
        }
        return false;
    }

    // assumes *list* is already sorted
    public static <T extends Comparable<T>> boolean binaryContains(List<T> list, T key) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) { // while there is still a search space
            int middle = (low + high) / 2;
            int comparison = list.get(middle).compareTo(key);
            if (comparison < 0) { // middle codon is less than key
                low = middle + 1;
            } else if (comparison > 0) { // middle codon is greater than key
                high = middle - 1;
            } else { // middle codon is equal to key
                return true;
            }
        }
        return false;
    }

    public static class Node<T> implements Comparable<Node<T>> {
        final T state;
        Node<T> parent;
        double cost;
        double heuristic;

        // for dfs and bfs we won't use cost and heuristic
        Node(T state, Node<T> parent) {
            assert state != null;
            assert parent != null;

            this.state = state;
            this.parent = parent;
        }

        // for astar we will use cost and heuristic
        Node(T state, Node<T> parent, double cost, double heuristic) {
            assert state != null;
            assert parent != null;

            this.state = state;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }

        @Override
        public int compareTo(Node<T> other) {
            assert other != null;

            Double mine = cost + heuristic;
            Double theirs = other.cost + other.heuristic;
            return mine.compareTo(theirs);
        }
    }

    public static <T> Optional<Node<T>> dfs(T initial, Predicate<T> goalTest, Function<T, List<T>> successors) {
        assert initial != null;
        assert goalTest != null;
        assert successors != null;

        final Stack<Node<T>> frontier = new Stack<>();
        frontier.push(new Node<>(initial, null));

        final Set<T> explored = new HashSet<>();
        explored.add(initial);

        while (!frontier.isEmpty()) {
            final Node<T> currentNode = frontier.pop();
            final T currentState = currentNode.state;

            if (goalTest.test(currentState)) {
                return Optional.of(currentNode);
            }

            for (T child : successors.apply(currentState)) {
                if (explored.contains(child)) {
                    continue;
                }

                explored.add(child);
                frontier.push(new Node<>(child, currentNode));
            }
        }
        return Optional.empty();
    }

    public static <T> List<T> nodeToPath(Node<T> node) {
        assert node != null;

        final ArrayList<T> path = new ArrayList<>();

        path.add(node.state);
        while (node.parent != null) {
            node = node.parent;
            path.add(0, node.state); // add to front
        }
        return path;
    }

    public static <T> Optional<Node<T>> bfs(T initial, Predicate<T> goalTest, Function<T, List<T>> successors) {
        assert initial != null;
        assert goalTest != null;
        assert successors != null;

        final Queue<Node<T>> frontier = new LinkedList<>();
        frontier.offer(new Node<>(initial, null));

        final Set<T> explored = new HashSet<>();
        explored.add(initial);

        while (!frontier.isEmpty()) {
            final Node<T> currentNode = frontier.poll();
            final T currentState = currentNode.state;

            if (goalTest.test(currentState)) {
                return Optional.of(currentNode);
            }

            for (T child : successors.apply(currentState)) {
                if (explored.contains(child)) {
                    continue;
                }
                explored.add(child);
                frontier.offer(new Node<>(child, currentNode));
            }
        }

        return Optional.empty();
    }

    public static <T> Optional<Node<T>> astar(T initial, Predicate<T> goalTest, Function<T, List<T>> successors, ToDoubleFunction<T> heuristic) {
        assert initial != null;
        assert goalTest != null;
        assert successors != null;
        assert heuristic != null;

        final Queue<Node<T>> frontier = new PriorityQueue<>();
        frontier.offer(new Node<>(initial, null, 0.0, heuristic.applyAsDouble(initial)));

        final Map<T, Double> explored = new HashMap<>();
        explored.put(initial, 0.0);

        while (!frontier.isEmpty()) {
            final Node<T> currentNode = frontier.poll();
            final T currentState = currentNode.state;

            if (goalTest.test(currentState)) {
                return Optional.of(currentNode);
            }

            for (T child : successors.apply(currentState)) {
                double newCost = currentNode.cost + 1;
                if (!explored.containsKey(child) || explored.get(child) > newCost) {
                    explored.put(child, newCost);
                    frontier.offer(new Node<>(child, currentNode, newCost, heuristic.applyAsDouble(child)));
                }
            }
        }

        return Optional.empty();
    }

    public static void main(String[] args) {
        System.out.println(GenericSearch.linearContains(List.of(1, 5, 15, 15, 15, 15, 20), 5)); // true
        System.out.println(GenericSearch.binaryContains(List.of("a", "d", "e", "f", "z"), "f")); // true
        System.out.println(GenericSearch.binaryContains(List.of("john", "mark", "ronald", "sarah"), "sheila")); // false
    }
}