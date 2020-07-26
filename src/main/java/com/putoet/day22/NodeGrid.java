package com.putoet.day22;

import java.util.*;
import java.util.stream.Collectors;

public class NodeGrid {
    private Node[][] nodes;

    public NodeGrid(List<Node> list){
        assert list != null;
        assert list.size() > 0;

        final OptionalInt maxX = list.stream().mapToInt(Node::x).max();
        final OptionalInt maxY = list.stream().mapToInt(Node::y).max();

        assert maxX.isPresent();
        assert maxY.isPresent();

        if ((maxX.getAsInt() + 1) * (maxY.getAsInt() + 1) != list.size())
            throw new IllegalStateException("List is not a complete grid");

        nodes = new Node[maxY.getAsInt() + 1][maxX.getAsInt() + 1];
        list.forEach(node -> nodes[node.y()][node.x()] = node);
    }

    public Node get(int x, int y) {
        assert x >= 0;
        assert y >= 0;
        assert x < nodes[0].length;
        assert y < nodes.length;

        return nodes[y][x];
    }

    @Override
    public String toString() {
        return Arrays.stream(nodes)
                .flatMap(Arrays::stream)
                .map(Objects::toString)
                .collect(Collectors.joining("[", ", ", "]"));
    }
}
