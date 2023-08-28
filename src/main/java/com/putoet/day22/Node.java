package com.putoet.day22;

public record Node(int x, int y, int size, int used) {

    public Node {
        assert x >= 0;
        assert y >= 0;
        assert size > 0;
        assert used >= 0;
    }

    public String name() {
        return DfParser.PREFIX + DfParser.SEPARATOR + "x" + x + DfParser.SEPARATOR + "y" + y;
    }

    public int free() { return size - used; }
    public int use() { return 100 * used/size; }
}
