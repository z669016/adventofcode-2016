package com.putoet.day9;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day9 {
    public static void main(String[] args) {
        final var line = ResourceLines.line("/day9.txt");

        Timer.run(() -> System.out.println("Length of decompressed file is " + Decompressor.decompress(line).length()));

        Timer.run(() -> System.out.println("Length of decompressed sequences is " + SequenceBuilder.from(line).length()));
    }
}
