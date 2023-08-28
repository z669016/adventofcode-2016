package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day20 {

    public static final long MAX_VALUE_INCLUSIVE = 4_294_967_295L;

    public static void main(String[] args) {
        final var set = new RangeSet();
        ResourceLines.list("/day20.txt").stream()
                .map(Range::of)
                .forEach(set::add);

        Timer.run(() -> {
            final var ranges = set.toList();
            final var lowest = ranges.get(0);
            System.out.println("lowest available IP address is " + (lowest.upperBound() + 1));
        });

        Timer.run(() ->
                System.out.println("Number of available IP addresses is " + set.availableCount(MAX_VALUE_INCLUSIVE))
        );
    }
}
