package com.putoet.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateBasedOperationTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {}));
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {"a", "b", "c", "d", "e", "f", "1"}));
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {"rotate", "b", "c", "d", "e", "f", "1"}));
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {"rotate", "based", "c", "d", "e", "f", "1"}));
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {"rotate", "based", "on", "d", "e", "f", "1"}));
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {"rotate", "based", "on", "position", "e", "f", "1"}));
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {"rotate", "based", "on", "position", "of", "f", "1"}));
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(new String[] {"rotate", "based", "on", "position", "of", "letter", "1"}));
    }

    @Test
    void apply() {
        final var rotate = new RotateBasedOperation("rotate based on position of letter d");
        assertEquals("ado", rotate.apply("doa"));
        assertEquals("oad", rotate.apply("oad"));
        assertEquals("cefdghiob", rotate.apply("obcefdghi"));
    }

    @Test
    void unApply() {
        final var rotate = new RotateBasedOperation("rotate based on position of letter a");
        assertEquals("abcdefgh", rotate.unApply(rotate.apply("abcdefgh")));
        assertEquals("habcdefg", rotate.unApply(rotate.apply("habcdefg")));
        assertEquals("ghabcdef", rotate.unApply(rotate.apply("ghabcdef")));
        assertEquals("fghabcde", rotate.unApply(rotate.apply("fghabcde")));
        assertEquals("efghabcd", rotate.unApply(rotate.apply("efghabcd")));
        assertEquals("defghabc", rotate.unApply(rotate.apply("defghabc")));
        assertEquals("cdefghab", rotate.unApply(rotate.apply("cdefghab")));
        assertEquals("bcdefgha", rotate.unApply(rotate.apply("bcdefgha")));
    }
}