package com.putoet.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveOperationTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new MoveOperation((String) null));
        assertThrows(AssertionError.class, () -> new MoveOperation(new String[] {}));
        assertThrows(AssertionError.class, () -> new MoveOperation(new String[] {"a", "b", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new MoveOperation(new String[] {"move", "b", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new MoveOperation(new String[] {"move", "position", "c", "d", "e", "f"}));
        assertThrows(AssertionError.class, () -> new MoveOperation(new String[] {"move", "position", "c", "to", "e", "f"}));
        assertThrows(NumberFormatException.class, () -> new MoveOperation(new String[] {"move", "position", "c", "to", "position", "f"}));
        assertThrows(NumberFormatException.class, () -> new MoveOperation(new String[] {"move", "position", "1", "to", "position", "f"}));
        assertThrows(AssertionError.class, () -> new MoveOperation(new String[] {"move", "position", "-1", "to", "position", "1"}));
        assertThrows(AssertionError.class, () -> new MoveOperation(new String[] {"move", "position", "1", "to", "position", "-1"}));
    }

    @Test
    void unApply() {
        final MoveOperation move = new MoveOperation("move position 1 to position 3");
        assertEquals("abcdef", move.unApply(move.apply("abcdef")));
    }

    @Test
    void applyBackToFront() {
        final MoveOperation move = new MoveOperation("move position 3 to position 0");
        assertEquals("dabc", move.apply("abcd"));
    }

    @Test
    void applyFrontToBack() {
        final MoveOperation move = new MoveOperation("move position 0 to position 3");
        assertEquals("bcda", move.apply("abcd"));
    }

    @Test
    void applyUnchanged() {
        final MoveOperation move = new MoveOperation("move position 1 to position 1");
        assertEquals("abcd", move.apply("abcd"));
    }

    @Test
    void applyError() {
        final MoveOperation move = new MoveOperation("move position 5 to position 11");
        assertThrows(IllegalArgumentException.class, () -> move.apply("abcd"));
        assertThrows(IllegalArgumentException.class, () -> move.apply("abcdefgh"));
    }
}