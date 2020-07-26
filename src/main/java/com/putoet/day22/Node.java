package com.putoet.day22;

import java.util.Objects;

public class Node {
    private final int x;
    private final int y;
    private int size;
    private int used;
    private boolean current;

    public Node(int x, int y, int size, int used) {
        assert x >= 0;
        assert y >= 0;
        assert size > 0;
        assert used >= 0;

        this.x = x;
        this.y = y;

        this.size = size;
        this.used = used;
        this.current = false;
    }

    public String name() {
        return DfParser.PREFIX + DfParser.SEPERATOR + "x" + x + DfParser.SEPERATOR + "y" + y;
    }

    public int x() { return x; }
    public int y() { return y; }
    public int size() { return size; }
    public int used() { return used; }
    public int free() { return size - used; }
    public int use() { return 100 * used/size; }

    public boolean isCurrent() { return current; }
    public void setCurrent() { current = true; }

    public void move(Node other) {
        if (!isAdjacent(other))
            throw new IllegalArgumentException(this.name() + " and " + other.name() + " are not adjacent nodes");
        if (other.free() < used)
            throw new IllegalStateException(other.toString() + " cannot receive content from " + this.toString());

        moveData(other);
        moveGoal(other);
    }

    private void moveGoal(Node other) {
        other.current = this.current;
        this.current = false;
    }

    private void moveData(Node other) {
        other.used += this.used;
        this.used = 0;
    }

    public boolean isAdjacent(Node other) {
        return ((x == other.x + 1 || x == other.x - 1) && (y == other.y)) ||
                ((x == other.x) && (y == other.y + 1 || y == other.y - 1));
    }

    @Override
    public String toString() {
        return "Node{" +
                "name=" + name() +
                ", size=" + size +
                ", used=" + used +
                ", goal=" + current +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y &&
                size == node.size &&
                used == node.used;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, size, used);
    }
}
