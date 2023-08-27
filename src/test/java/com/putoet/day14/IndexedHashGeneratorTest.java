package com.putoet.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexedHashGeneratorTest {

    @Test
    void get() {
        final var supplier = new IndexedHashPeekableSupplier(new IndexedHashSupplier("abc"));
        final var generator = new IndexedHashGenerator(supplier);

        for (var idx = 1; idx < 65; idx++) {
            final var ih = generator.get();

            if (idx == 1)
                assertEquals(39, ih.index());
            if (idx == 64)
                assertEquals(22728, ih.index());
        }
    }

    @Test
    void getStretched() {
        final var supplier = new IndexedHashPeekableSupplier(new IndexStretchedHashSupplier("abc"));
        final var generator = new IndexedHashGenerator(supplier);

        for (var idx = 1; idx < 65; idx++) {
            final var ih = generator.get();

            if (idx == 1)
                assertEquals(10, ih.index());
            if (idx == 64)
                assertEquals(22551, ih.index());
        }
    }
}