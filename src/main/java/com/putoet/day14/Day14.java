package com.putoet.day14;

public class Day14 {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    private static void partOne() {
        final IndexedHashPeekableSupplier supplier = new IndexedHashPeekableSupplier(new IndexedHashSupplier("ngcjuoqr"));
        final IndexedHashGenerator generator = new IndexedHashGenerator(supplier);

        IndexedHash ih = null;
        for (int idx = 0; idx < 64; idx++)
            ih = generator.get();

        System.out.println("Index of 64th hash is " + ih.index());
    }

    private static void partTwo() {
        final IndexedHashPeekableSupplier supplier = new IndexedHashPeekableSupplier(new IndexStretchedHashSupplier("ngcjuoqr"));
        final IndexedHashGenerator generator = new IndexedHashGenerator(supplier);

        IndexedHash ih = null;
        for (int idx = 0; idx < 64; idx++)
            ih = generator.get();

        System.out.println("Index of 64th stretched hash is " + ih.index());
    }
}
