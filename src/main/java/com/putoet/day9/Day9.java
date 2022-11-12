package com.putoet.day9;

import com.putoet.resources.ResourceLines;

public class Day9 {
    public static void main(String[] args) {
        final var line = ResourceLines.line("/day9.txt");

        System.out.println("Length of decompressed file is " + Decompressor.decompress(line).length());

        final Sequence sequence = SequenceBuilder.from(line);
        System.out.println("Length of decompressed sequences is " + sequence.length());
    }
}
