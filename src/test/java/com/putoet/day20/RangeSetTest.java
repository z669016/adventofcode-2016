package com.putoet.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeSetTest {

    @Test
    void addNoOverlap() {
        final RangeSet set = new RangeSet();
        set.add(Range.of(0,2));
        set.add(Range.of(4, 6));

        assertEquals(2, set.size());
    }

    @Test
    void addStartOverlap() {
        final var set = new RangeSet();
        set.add(Range.of(3, 6));
        set.add(Range.of(0,3));

        assertEquals(1, set.size());
    }

    @Test
    void addEndOverlap() {
        final var set = new RangeSet();
        set.add(Range.of(0,3));
        set.add(Range.of(3, 6));

        assertEquals(1, set.size());
    }

    @Test
    void addEConsequtiveverlap() {
        final var set = new RangeSet();
        set.add(Range.of(0,3));
        set.add(Range.of(4, 6));

        assertEquals(1, set.size());
    }

    @Test
    void addMidOverlap() {
        final var set = new RangeSet();
        set.add(Range.of(0,3));
        set.add(Range.of(6, 9));
        set.add(Range.of(3, 6));

        assertEquals(1, set.size());
    }

    @Test
    void availableCount() {
        final var set = new RangeSet();
        set.add(Range.of(0,1));
        set.add(Range.of(3, 4));
        set.add(Range.of(6, 8));

        assertEquals(4, set.availableCount(10L));
    }
}