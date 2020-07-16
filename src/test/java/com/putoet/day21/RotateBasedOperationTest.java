package com.putoet.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateBasedOperationTest {
    @Test
    void create() {
        final String nul = null;
        assertThrows(AssertionError.class, () -> new RotateBasedOperation(nul));
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
        final RotateBasedOperation rotate = new RotateBasedOperation("rotate based on position of letter d");
        assertEquals("ado", rotate.apply("doa"));
        assertEquals("oad", rotate.apply("oad"));
        assertEquals("cefdghiob", rotate.apply("obcefdghi"));
    }
}