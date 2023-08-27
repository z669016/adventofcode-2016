package com.putoet.day14;

import com.putoet.utils.Timer;

import java.util.function.Supplier;

public class Day14 {
    public static void main(String[] args) {
        Timer.run(() -> part(new IndexedHashSupplier("ngcjuoqr")));
        Timer.run(() -> part(new IndexStretchedHashSupplier("ngcjuoqr")));
    }

    private static void part(Supplier<IndexedHash> supplier) {
        final var generator = new IndexedHashGenerator(new IndexedHashPeekableSupplier(supplier));

        IndexedHash ih = null;
        for (var idx = 0; idx < 64; idx++)
            ih = generator.get();

        System.out.println("Index of 64th " + supplier + " is " + ih.index());
    }
}
