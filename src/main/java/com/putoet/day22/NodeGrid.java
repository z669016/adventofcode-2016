package com.putoet.day22;

import java.util.*;
import java.util.stream.Collectors;

public class NodeGrid {
    private final Node[][] nodes;
    private final Node empty;
    private final int maxX;
    private final int maxY;

    public NodeGrid(List<Node> list){
        assert list != null;
        assert list.size() > 0;

        maxX = list.stream().mapToInt(Node::x).max().getAsInt();
        maxY = list.stream().mapToInt(Node::y).max().getAsInt();

        if ((maxX + 1) * (maxY + 1) != list.size())
            throw new IllegalStateException("List is not a complete grid");

        nodes = new Node[maxY + 1][maxX + 1];
        empty = list.stream().filter(node -> node.used() == 0).findFirst().get();
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

    void print() {
        final StringBuilder line1 = new StringBuilder("    ");
        final StringBuilder line2 = new StringBuilder("    ");
        for (int x = 0; x < nodes[0].length; x++) {
            line1.append(" ").append(x / 10 == 0 ? " " : (x/10)).append(" ");
            line2.append(" ").append(x % 10).append(" ");
        }
        System.out.println(line1.toString());
        System.out.println(line2.toString());
        for (int y = 0; y < nodes.length; y++) {
            System.out.printf("%3d ", y);
            for (int x = 0; x < nodes[y].length; x++) {
                System.out.print(nodeAsChar(nodes[y][x]));
            }
            System.out.println();
        }
        System.out.println();
    }

    private String nodeAsChar(Node node) {
        assert node != null;

        if (node.x() == 0 && node.y() == 0)
            return "(.)";

        if (node.x() == maxX && node.y() == 0)
            return " G ";

        if (empty.equals(node))
            return "[_]";

        if (node.used() > empty.free())
            return " # ";

        return " . ";
    }
}
