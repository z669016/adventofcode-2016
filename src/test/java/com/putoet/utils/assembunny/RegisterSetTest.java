package com.putoet.utils.assembunny;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegisterSetTest {
    private static final String AX = "ax";
    private static final String BX = "bx";
    private static final String CX = "cx";
    private static final String DX = "dx";

    private final Register ax = new Register(AX);
    private final Register bx = new Register(BX);
    private final Register cx = new Register(CX);
    private RegisterSet set;

    @BeforeEach
    void setup() {
        set = RegisterSet.of(ax, bx, cx);
    }

    @Test
    void get() {
        List.of(AX, BX, CX).forEach(id -> {
            final Optional<Register> reg = set.get(id);
            assertTrue(reg.isPresent());
            assertEquals(id, reg.get().name());
        });

        assertFalse(set.get(DX).isPresent());
    }

    @Test
    void testToString() {
        assertEquals("[ax:0, bx:0, cx:0]", set.toString());
    }
}