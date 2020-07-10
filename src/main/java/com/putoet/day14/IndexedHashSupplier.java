package com.putoet.day14;

import com.putoet.utils.MD5;

import java.util.function.Supplier;

public class IndexedHashSupplier implements Supplier<IndexedHash> {
    private final String salt;

    private int index = 0;

    public IndexedHashSupplier(String salt) {
        assert salt != null;
        assert salt.length() > 0;

        this.salt = salt;
    }

    @Override
    public IndexedHash get() {
        final String code = salt + index;
        final String hash = MD5.hash(code).toLowerCase();
        return new IndexedHash(index++, hash);
    }
}
