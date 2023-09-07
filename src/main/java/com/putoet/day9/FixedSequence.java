package com.putoet.day9;

record FixedSequence(String text) implements Sequence {
    public long length() {
        return text.length();
    }

    @Override
    public String toString() {
        return text;
    }
}
