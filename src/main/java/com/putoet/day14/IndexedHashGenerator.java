package com.putoet.day14;

import java.util.function.Supplier;

class IndexedHashGenerator implements Supplier<IndexedHash> {
    private static final int RANGE = 1_000;

    private final IndexedHashPeekableSupplier supplier;

    public IndexedHashGenerator(IndexedHashPeekableSupplier supplier) {
        assert supplier != null;

        this.supplier = supplier;
    }

    @Override
    public IndexedHash get() {
        var ih = supplier.get();
        while (!isValid(ih))
            ih = supplier.get();

        return ih;
    }

    private boolean isValid(IndexedHash ih) {
        final var triplet = SequenceValidator.triplet(ih.hash());
        if (triplet.isEmpty())
            return false;

        for (var idx = 0; idx < RANGE; idx++) {
            final var peek = supplier.peek(idx);
            if (SequenceValidator.fivelet(triplet.get(), peek.hash()))
                return true;
        }

        return false;
    }
}
