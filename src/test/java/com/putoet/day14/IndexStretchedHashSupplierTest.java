package com.putoet.day14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexStretchedHashSupplierTest {
    private IndexStretchedHashSupplier supplier;

    @BeforeEach
    void setup() {
        supplier = new IndexStretchedHashSupplier("abc");
    }

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new IndexStretchedHashSupplier(null));
        assertThrows(AssertionError.class, () -> new IndexStretchedHashSupplier(""));
    }
    @Test
    void get() {
        assertEquals("a107ff634856bb300138cac6568c0f24", supplier.get().hash());
    }

}