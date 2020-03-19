package com.putoet.day9;

import com.putoet.utils.ResourceLines;

import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day9.txt");

        System.out.println("Length of decompressed file is " + Decompressor.decompress(lines.get(0)).length());

        final Sequence sequence = SequenceBuilder.from(lines.get(0));
        System.out.println("Length of decompressed sequences is " + sequence.length());
    }
}
