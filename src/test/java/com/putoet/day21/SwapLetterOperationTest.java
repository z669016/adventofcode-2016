package com.putoet.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapLetterOperationTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"a", "b", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"swap", "b", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"swap", "letter", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"swap", "letter", "c", "to", "e", "f"}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"swap", "letter", "1", "with", "letter", "f"}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"swap", "letter", "c", "with", "letter", "1"}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"swap", "letter", "cc", "with", "letter", "d"}));
        assertThrows(AssertionError.class, () -> new SwapLetterOperation(new String[] {"swap", "letter", "c", "with", "letter", "dd"}));
    }

    @Test
    void apply() {
        final var swap = new SwapLetterOperation("swap letter a with letter d");
        assertEquals("dbca", swap.apply("abcd"));
        assertEquals("abcd", swap.unApply(swap.apply("abcd")));
    }

    @Test
    void applyUnchanged() {
        final var swap = new SwapLetterOperation("swap letter a with letter a");
        assertEquals("abcd", swap.apply("abcd"));
    }

    @Test
    void applyNotFound() {
        final var swap = new SwapLetterOperation("swap letter q with letter t");
        assertThrows(IllegalArgumentException.class, () -> swap.apply("abcd"));
    }
}