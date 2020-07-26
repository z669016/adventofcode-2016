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
    void size() {
        assertEquals(SIZE, node.size());
    }

    @Test
    void used() {
        assertEquals(USED, node.used());
    }

    @Test
    void free() {
        assertEquals(SIZE - USED, node.free());
    }

    @Test
    void use() {
        assertEquals(1, node.use());
    }

    @Test
    void move() {
        assertThrows(IllegalArgumentException.class, () -> node.move(new Node(X+1, Y+1, 1000, 0)));
        assertThrows(IllegalStateException.class, () -> node.move(new Node(X+1, Y, 1, 1)));

        final Node to = new Node(X+1, Y, 1000, 0);
        node.move(to);
        assertEquals(USED, to.used());
        assertEquals(0, node.used());
    }

    @Test
    void isAdjacent() {
        assertTrue(node.isAdjacent(new Node(X + 1, Y, 1, 0)));
        assertTrue(node.isAdjacent(new Node(X - 1, Y, 1, 0)));
        assertTrue(node.isAdjacent(new Node(X, Y + 1, 1, 0)));
        assertTrue(node.isAdjacent(new Node(X, Y - 1, 1, 0)));

        assertFalse(node.isAdjacent(new Node(X + 1, Y + 1, 1, 0)));
        assertFalse(node.isAdjacent(new Node(X - 1, Y - 1, 1, 0)));
        assertFalse(node.isAdjacent(node));
    }
}