package com.putoet.day14;

import java.util.function.Supplier;

public class Day14 {
    public static void main(String[] args) {
        part(new IndexedHashSupplier("ngcjuoqr"));
        part(new IndexStretchedHashSupplier("ngcjuoqr"));
    }

    private static void part(Supplier<IndexedHash> supplier) {
        final IndexedHashGenerator generator = new IndexedHashGenerator(new IndexedHashPeekableSupplier(supplier));

        IndexedHash ih = null;
        for (int idx = 0; idx < 64; idx++)
            ih = generator.get();

        System.out.println("Index of 64th " + supplier + " is " + ih.index());
    }
}
