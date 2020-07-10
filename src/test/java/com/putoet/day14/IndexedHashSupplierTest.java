package com.putoet.day14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexedHashSupplierTest {
    private IndexedHashSupplier supplier;

    @BeforeEach
    void setup() {
        supplier = new IndexedHashSupplier("abc");
    }

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new IndexedHashSupplier(null));
        assertThrows(AssertionError.class, () -> new IndexedHashSupplier(""));
    }
    @Test
    void get() {
        IndexedHash ih = supplier.get();
        while (ih.index() < 18)
            ih = supplier.get();

        assertTrue(ih.hash().contains("cc38887a5"));
        System.out.println(ih);
    }
}