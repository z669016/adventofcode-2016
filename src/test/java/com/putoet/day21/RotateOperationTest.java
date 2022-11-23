package com.putoet.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateOperationTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new RotateOperation((String) null));
        assertThrows(AssertionError.class, () -> new RotateOperation(new String[] {}));
        assertThrows(AssertionError.class, () -> new RotateOperation(new String[] {"a", "b", "c", "d"}));
        assertThrows(AssertionError.class, () -> new RotateOperation(new String[] {"rotate", "b", "c", "d"}));
        assertThrows(AssertionError.class, () -> new RotateOperation(new String[] {"rotate", "left", "c", "d"}));
        assertThrows(AssertionError.class, () -> new RotateOperation(new String[] {"rotate", "right", "1", "d"}));
        assertThrows(AssertionError.class, () -> new RotateOperation(new String[] {"rotate", "left", "-1", "steps"}));
        assertThrows(AssertionError.class, () -> new RotateOperation(new String[] {"rotate", "right", "-1", "steps"}));
    }

    @Test
    void applyLeft() {
        final RotateOperation rotateLeft = new RotateOperation("rotate left 3 steps");
        assertEquals("a", rotateLeft.apply("a"));
        assertEquals("defgabc", rotateLeft.apply("abcdefg"));
        assertEquals("abcdefg", rotateLeft.unApply(rotateLeft.apply("abcdefg")));
    }

    @Test
    void applyRight() {
        final RotateOperation rotateRight = new RotateOperation("rotate right 3 steps");
        assertEquals("a", rotateRight.apply("a"));
        assertEquals("efgabcd", rotateRight.apply("abcdefg"));
        assertEquals("abcdefg", rotateRight.unApply(rotateRight.apply("abcdefg")));
    }
}