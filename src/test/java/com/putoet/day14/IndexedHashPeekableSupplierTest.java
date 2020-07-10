package com.putoet.day14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexedHashPeekableSupplierTest {
    private IndexedHashSupplier supplier;
    private IndexedHashPeekableSupplier generator;

    @BeforeEach
    void setup() {
        supplier = new IndexedHashSupplier("abc");
        generator = new IndexedHashPeekableSupplier(supplier);
    }

    @Test
    void get() {
        assertEquals(0, generator.get().index());
        assertEquals(1, generator.get().index());
        assertEquals(2, generator.get().index());
    }

    @Test
    void peek() {
        final IndexedHashSupplier spy = spy(supplier);
        final IndexedHashPeekableSupplier generator = new IndexedHashPeekableSupplier(spy);

        assertEquals(0, generator.peek(0).index());
        assertEquals(10, generator.peek(10).index());
        assertEquals(20, generator.peek(20).index());

        assertEquals(0, generator.get().index());
        assertEquals(1, generator.get().index());
        assertEquals(2, generator.get().index());

        verify(spy, times(21)).get();
    }

    @Test
    void check() {
        final Optional<String> triplet = SequenceValidator.triplet(generator.peek(39).hash());
        assertTrue(triplet.isPresent());
        assertEquals("e", triplet.get());
        assertTrue(SequenceValidator.fivelet(triplet.get(), generator.peek(816).hash()));
    }
}