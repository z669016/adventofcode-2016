package com.putoet.day22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private static final int X = 2, Y = 3, SIZE = 200, USED = 3;

    private Node node;

    @BeforeEach
    void setup() {
        node = new Node(X, Y, SIZE, USED);
    }

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Node(-1, 1, 1, 1));
        assertThrows(AssertionError.class, () -> new Node(1, -1, 1, 1));
        assertThrows(AssertionError.class, () -> new Node(1, 1, 0, 1));
        assertThrows(AssertionError.class, () -> new Node(1, 1, 1, -1));
    }

    @Test
    void name() {
        assertEquals("/dev/grid/node-x2-y3", node.name());
    }

    @Test
    void free() {
        assertEquals(SIZE - USED, node.free());
    }

    @Test
    void use() {
        assertEquals(1, node.use());
    }
}