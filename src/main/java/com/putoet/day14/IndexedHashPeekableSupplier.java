package com.putoet.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

class IndexedHashPeekableSupplier implements Supplier<IndexedHash>, Peekable<IndexedHash> {
    private final Supplier<IndexedHash> supplier;
    private final List<IndexedHash> cache = new ArrayList<>();

    public IndexedHashPeekableSupplier(Supplier<IndexedHash> supplier) {
        assert supplier != null;

        this.supplier = supplier;
    }

    @Override
    public IndexedHash get() {
        return !cache.isEmpty() ? cache.remove(0) : supplier.get();
    }

    @Override
    public IndexedHash peek(int offset) {
        assert offset >= 0;

        while (offset >= cache.size())
            cache.add(supplier.get());

        return cache.get(offset);
    }
}
