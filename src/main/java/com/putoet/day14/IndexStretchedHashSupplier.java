package com.putoet.day14;

import com.putoet.utils.MD5;

import java.util.function.Supplier;

public class IndexStretchedHashSupplier implements Supplier<IndexedHash> {
    private static final int STRETCH = 2016;

    private final String salt;

    private int index = 0;

    public IndexStretchedHashSupplier(String salt) {
        assert salt != null;
        assert salt.length() > 0;

        this.salt = salt;
    }

    @Override
    public IndexedHash get() {
        final String code = salt + index;
        String hash = MD5.hash(code).toLowerCase();

        for (int idx = 0; idx < STRETCH; idx++)
            hash = MD5.hash(hash).toLowerCase();

        return new IndexedHash(index++, hash);
    }
}