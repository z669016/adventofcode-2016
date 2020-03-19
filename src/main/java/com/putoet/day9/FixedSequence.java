package com.putoet.day9;

public class FixedSequence implements Sequence {
    private final String text;

    public FixedSequence(String text) {
        this.text = text;
    }

    public String text() { return text; }
    public long length() { return text.length(); }

    @Override
    public String toString() {
        return text;
    }
}
