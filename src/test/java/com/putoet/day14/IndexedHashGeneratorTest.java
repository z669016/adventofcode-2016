package com.putoet.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexedHashGeneratorTest {

    @Test
    void get() {
        final IndexedHashPeekableSupplier supplier = new IndexedHashPeekableSupplier(new IndexedHashSupplier("abc"));
        final IndexedHashGenerator generator = new IndexedHashGenerator(supplier);

        for (int idx = 1; idx < 65; idx++) {
            final IndexedHash ih = generator.get();

            if (idx == 1)
                assertEquals(39, ih.index());
            if (idx == 64)
                assertEquals(22728, ih.index());
        }
    }

    @Test
    void getStretched() {
        final IndexedHashPeekableSupplier supplier = new IndexedHashPeekableSupplier(new IndexStretchedHashSupplier("abc"));
        final IndexedHashGenerator generator = new IndexedHashGenerator(supplier);

        for (int idx = 1; idx < 65; idx++) {
            final IndexedHash ih = generator.get();

            if (idx == 1)
                assertEquals(10, ih.index());
            if (idx == 64)
                assertEquals(22551, ih.index());
        }
    }
}