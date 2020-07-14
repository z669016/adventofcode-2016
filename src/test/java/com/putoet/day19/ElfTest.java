package com.putoet.day19;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {
    @Test
    void get() {
        final Supplier<Elf> supplier = Elf.supplier();
        assertEquals(1, supplier.get().id());
        assertEquals(2, supplier.get().id());
        assertEquals(3, supplier.get().id());
        assertEquals(4, supplier.get().id());
        assertEquals(5, supplier.get().id());
        assertEquals(6, supplier.get().id());
    }

    @Test
    void isSkipped() {
        final Supplier<Elf> supplier = Elf.supplier();
        Elf one = supplier.get(), two = supplier.get();

        assertEquals(1, one.presents());
        assertEquals(1, two.presents());

        assertFalse(one.isSkipped());
        assertFalse(two.isSkipped());

        one.stealFrom(two);

        assertEquals(2, one.presents());
        assertEquals(0, two.presents());

        assertFalse(one.isSkipped());
        assertTrue(two.isSkipped());
    }
}