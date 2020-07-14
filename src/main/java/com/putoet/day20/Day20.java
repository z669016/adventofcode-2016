package com.putoet.day20;

import com.putoet.utils.ResourceLines;

import java.util.List;

public class Day20 {
    public static void main(String[] args) {
        final RangeSet set = new RangeSet();
        ResourceLines.list("/day20.txt").stream()
                .map(Range::of)
                .forEach(set::add);

        final List<Range> ranges = set.toList();
        ranges.forEach(System.out::println);

        if (ranges.size() > 0) {
            final Range lowest = ranges.get(0);
            System.out.println("lowest available IP address is " + (lowest.upperBound() + 1));
        }

        final long maxValue = 4_294_967_295L;
        System.out.println("Number of available IP addresses is " + set.availableCount(maxValue));
    }
}
