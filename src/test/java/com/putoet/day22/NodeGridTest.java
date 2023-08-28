package com.putoet.day22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeGridTest {
    public static final List<String> NODES_LINES = List.of(
            "/dev/grid/node-x0-y0   10T    8T     2T   80%",
            "/dev/grid/node-x0-y1   11T    6T     5T   54%",
            "/dev/grid/node-x0-y2   32T   28T     4T   87%",
            "/dev/grid/node-x1-y0    9T    7T     2T   77%",
            "/dev/grid/node-x1-y1    8T    0T     8T    0%",
            "/dev/grid/node-x1-y2   11T    7T     4T   63%",
            "/dev/grid/node-x2-y0   10T    6T     4T   60%",
            "/dev/grid/node-x2-y1    9T    8T     1T   88%",
            "/dev/grid/node-x2-y2    9T    6T     3T   66%"
    );
    private List<Node> nodeList;
    private NodeGrid grid;

    @BeforeEach
    void setup() {
        nodeList = DfParser.parse(NODES_LINES);
        grid = new NodeGrid(nodeList);
    }

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new NodeGrid(List.of()));

        assertThrows(IllegalStateException.class, () -> new NodeGrid(List.of(
                new Node(1, 0, 10, 1),
                new Node(2, 0, 10, 1)
        )));

        assertThrows(IllegalStateException.class, () -> new NodeGrid(List.of(
                new Node(0, 1, 10, 1),
                new Node(1, 1, 10, 1)
        )));
    }

    @Test
    void get() {
        final var one = new Node(0, 0, 1, 0);
        final var two = new Node(1, 0, 2, 0);
        final var three = new Node(0, 1, 3, 0);
        final var four = new Node(1, 1, 4, 0);

        final var grid = new NodeGrid(List.of(four, one, two, three));
        assertEquals(one, grid.get(0, 0));
        assertEquals(two, grid.get(1, 0));
        assertEquals(three, grid.get(0, 1));
        assertEquals(four, grid.get(1, 1));
    }
}