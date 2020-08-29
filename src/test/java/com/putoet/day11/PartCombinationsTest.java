package com.putoet.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartCombinationsTest {
    private final RTG hydrogenRtg = new RTG("hydrogen");
    private final RTG lithiumRtg = new RTG("lithium");
    private final RTG eleriumRtg = new RTG("elerium");
    private final Chip hydrogenChip = new Chip("hydrogen");
    private final Chip lithiumChip = new Chip("lithium");
    private final Chip eleriumChip = new Chip("elerium");

    @Test
    void add() {
        final PartCombinations combinations = new PartCombinations();

        // You can add an RTG to an empty set
        assertTrue(combinations.add(hydrogenRtg));
        combinations.remove(hydrogenRtg);

        // You can add any chip to an empty set
        assertTrue(combinations.add(hydrogenChip));

        // You cannot add the same chip twice
        assertThrows(AssertionError.class, () -> combinations.add(hydrogenChip));

        // You cannot add an RTG if an unconnected chip for another RTG is in the set
        assertFalse(combinations.add(lithiumRtg));

        // You can add an RTG which chip is the only unconnected chip in the set
        assertTrue(combinations.add(hydrogenRtg));

        // You cannot add the same RTG twice
        assertThrows(AssertionError.class, () -> combinations.add(hydrogenRtg));

        // You cannot add a chip if there is another RTG and the chip will be unconnected
        assertFalse(combinations.add(lithiumChip));

        // You can add a RTG when there is no unconnected chip
        assertTrue(combinations.add(lithiumRtg));

        // You can add a chip when it's connected
        assertTrue(combinations.add(lithiumChip));
    }

    @Test
    void addTwo() {
        PartCombinations combinations = new PartCombinations();
        // You can add two chips if there are no RTG's
        assertTrue(combinations.add(eleriumChip));
        assertTrue(combinations.add(hydrogenChip, lithiumChip));

        combinations = new PartCombinations();
        combinations.add(hydrogenRtg);

        // You cannot add two chips if one of their RTG's is missing
        assertFalse(combinations.add(hydrogenChip, lithiumChip));

        combinations.add(lithiumRtg);

        // You can add two chips if boths RTG's are there
        assertTrue(combinations.add(hydrogenChip, lithiumChip));

        // You can add two RTG's when the set is empty
        combinations = new PartCombinations();
        assertTrue(combinations.add(eleriumRtg));
        assertTrue(combinations.add(hydrogenRtg, lithiumRtg));

        combinations = new PartCombinations();
        combinations.add(hydrogenChip);

        // You can add two RTG's if there is only one unconnected chip
        assertTrue(combinations.add(hydrogenRtg, lithiumRtg));

        combinations = new PartCombinations();
        combinations.add(hydrogenChip);

        // You can add two RTG's if there is only one unconnected chip
        assertTrue(combinations.add(hydrogenRtg, lithiumRtg));
    }

    @Test
    void remove() {
        final PartCombinations combinations = new PartCombinations();
        assertThrows(AssertionError.class, () -> combinations.remove(hydrogenRtg));
        assertThrows(AssertionError.class, () -> combinations.remove(hydrogenChip));

        // You can remove an RTG when it's the only part
        assertTrue(combinations.add(hydrogenRtg));
        assertTrue(combinations.remove(hydrogenRtg));

        assertTrue(combinations.add(hydrogenRtg));
        assertTrue(combinations.add(hydrogenChip));

        // You can remove an RTG when it's chip is the only thing staying
        assertTrue(combinations.remove(hydrogenRtg));
        assertTrue(combinations.add(hydrogenRtg));

        assertTrue(combinations.add(lithiumRtg));
        assertTrue(combinations.add(lithiumChip));

        assertFalse(combinations.remove(hydrogenRtg));
        assertTrue(combinations.remove(hydrogenChip));
    }

    @Test
    void removeTwo() {
        final PartCombinations combinations = new PartCombinations();
        assertThrows(AssertionError.class, () -> combinations.remove(hydrogenRtg, lithiumRtg));
        assertThrows(AssertionError.class, () -> combinations.remove(hydrogenChip, lithiumChip));

        // You can remove two RTG when they're the only parts in the set
        assertTrue(combinations.add(eleriumRtg));
        assertTrue(combinations.add(hydrogenRtg, lithiumRtg));
        assertTrue(combinations.remove(hydrogenRtg, lithiumRtg));
        assertTrue(combinations.remove(eleriumRtg));

        // You can remove two chips when they're the only parts in the set
        assertTrue(combinations.add(eleriumChip));
        assertTrue(combinations.add(hydrogenChip, lithiumChip));
        assertTrue(combinations.remove(hydrogenChip, lithiumChip));
    }
}