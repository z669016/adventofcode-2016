package com.putoet.day14;

import java.util.Optional;
import java.util.function.Supplier;

public class IndexedHashGenerator implements Supplier<IndexedHash> {
    private static final int RANGE = 1_000;

    private final IndexedHashPeekableSupplier supplier;

    public IndexedHashGenerator(IndexedHashPeekableSupplier supplier) {
        assert supplier != null;

        this.supplier = supplier;
    }

    @Override
    public IndexedHash get() {
        IndexedHash ih = supplier.get();
        while (!isValid(ih))
            ih = supplier.get();

        return ih;
    }

    private boolean isValid(IndexedHash ih) {
        final Optional<String> triplet = SequenceValidator.triplet(ih.hash());
        if (triplet.isEmpty())
            return false;

        for (int idx = 0; idx < RANGE; idx++) {
            final IndexedHash peek = supplier.peek(idx);
            if (SequenceValidator.fivelet(triplet.get(), peek.hash()))
                return true;
        }

        return false;
    }
}
