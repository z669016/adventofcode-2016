package com.putoet.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseOperationTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new ReverseOperation(new String[] {}));
        assertThrows(AssertionError.class, () -> new ReverseOperation(new String[] {"a", "b", "c", "d", "e"}));
        assertThrows(AssertionError.class, () -> new ReverseOperation(new String[] {"reverse", "positions", "c", "d", "e"}));
        assertThrows(AssertionError.class, () -> new ReverseOperation(new String[] {"reverse", "positions", "c", "to", "e"}));
        assertThrows(NumberFormatException.class, () -> new ReverseOperation(new String[] {"reverse", "positions", "c", "through", "1"}));
        assertThrows(NumberFormatException.class, () -> new ReverseOperation(new String[] {"reverse", "positions", "1", "through", "e"}));
        assertThrows(AssertionError.class, () -> new ReverseOperation(new String[] {"reverse", "positions", "-1", "through", "1"}));
        assertThrows(AssertionError.class, () -> new ReverseOperation(new String[] {"reverse", "positions", "1", "through", "-1"}));
    }

    @Test
    void unAapply() {
        final var reverse = new ReverseOperation("reverse positions 1 through 4");
        assertEquals("abcdef", reverse.unApply(reverse.apply("abcdef")));
    }

    @Test
    void applyError() {
        final var reverse = new ReverseOperation("reverse positions 4 through 6");
        assertThrows(IllegalArgumentException.class, () -> reverse.apply("abc"));
        assertThrows(IllegalArgumentException.class, () -> reverse.apply("abcde"));
    }

    @Test
    void applyMiddle() {
        final var reverse = new ReverseOperation("reverse positions 1 through 4");
        assertEquals("aedcbf", reverse.apply("abcdef"));
    }

    @Test
    void applyStart() {
        final var reverse = new ReverseOperation("reverse positions 0 through 3");
        assertEquals("dcbaef", reverse.apply("abcdef"));
    }

    @Test
    void applyEnd() {
        final var reverse = new ReverseOperation("reverse positions 2 through 5");
        assertEquals("abfedc", reverse.apply("abcdef"));
    }
}