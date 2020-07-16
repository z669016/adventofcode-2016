package com.putoet.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapPositionOperationTest {
    @Test
    void create() {
        final String nul = null;
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(nul));
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(new String[] {}));
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(new String[] {"a", "b", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(new String[] {"swap", "b", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(new String[] {"swap", "position", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(new String[] {"swap", "position", "c", "to", "e", "f"}));
        assertThrows(NumberFormatException.class, () -> new SwapPositionOperation(new String[] {"swap", "position", "c", "with", "position", "f"}));
        assertThrows(NumberFormatException.class, () -> new SwapPositionOperation(new String[] {"swap", "position", "1", "with", "position", "f"}));
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(new String[] {"swap", "position", "-1", "with", "position", "1"}));
        assertThrows(AssertionError.class, () -> new SwapPositionOperation(new String[] {"swap", "position", "1", "with", "position", "-1"}));
    }

    @Test
    void applyBackToFront() {
        final SwapPositionOperation swap = new SwapPositionOperation("swap position 3 with position 0");
        assertEquals("dbca", swap.apply("abcd"));
    }

    @Test
    void applyFrontToBack() {
        final SwapPositionOperation swap = new SwapPositionOperation("swap position 0 with position 3");
        assertEquals("dbca", swap.apply("abcd"));
    }

    @Test
    void applyUnchanged() {
        final SwapPositionOperation swap = new SwapPositionOperation("swap position 1 with position 1");
        assertEquals("abcd", swap.apply("abcd"));
    }

    @Test
    void applyError() {
        final SwapPositionOperation swap = new SwapPositionOperation("swap position 5 with position 11");
        assertThrows(IllegalArgumentException.class, () -> swap.apply("abcd"));
        assertThrows(IllegalArgumentException.class, () -> swap.apply("abcdefgh"));
    }
}