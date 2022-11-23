package com.putoet.day20;

import java.util.*;

public class RangeSet {
    private final Set<Range> set = new HashSet<>();

    public void add(Range range) {
        if (set.contains(range))
            return;

        final List<Range> overlaps = overlaps(range);
        Range merge = range;
        for (Range overlap : overlaps) {
            merge = merge.merge(overlap);
            set.remove(overlap);
        }
        set.remove(range);
        set.add(merge);
    }

    protected List<Range> overlaps(Range range) {
        return set.stream().filter(r -> r.overlaps(range)).toList();
    }

    public int size() {
        return set.size();
    }

    public List<Range> toList() {
        return set.stream().sorted().toList();
    }

    @Override
    public String toString() {
        return set.toString();
    }

    public long availableCount(long maxValue) {
        final long used = set.stream()
                .mapToLong(range -> range.upperBound() - range.lowerBound() + 1)
                .sum();
        return maxValue + 1 - used;
    }
}
