package com.putoet.day22;

import com.putoet.utils.ResourceLines;

import java.util.List;

public class Day22 {
    public static void main(String[] args) {
        final List<Node> nodes = DfParser.parse(ResourceLines.list("/day22.txt"));
        System.out.println("Number of viable pairs: " + viablePairs(nodes));
    }

    private static long viablePairs(List<Node> nodes) {
        return nodes.stream()
                .filter(node -> node.used() > 0)
                .mapToLong(node -> nodes.stream()
                            .filter(other -> !other.equals(node) && other.free() >= node.used())
                            .count()
                )
                .sum();
    }
}
