package com.putoet.day14;

public class IndexedHash {
    private final int index;
    private final String hash;

    public IndexedHash(int index, String hash) {
        this.index = index;
        this.hash = hash;
    }

    public int index() { return index; }
    public String hash() { return hash; }

    @Override
    public String toString() {
        return String.format("{index:%d, hash:%s}", index, hash);
    }
}
