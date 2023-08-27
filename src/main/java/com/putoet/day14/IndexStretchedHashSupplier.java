package com.putoet.day14;

import com.putoet.security.MD5;
import lombok.SneakyThrows;

import java.util.function.Supplier;

class IndexStretchedHashSupplier implements Supplier<IndexedHash> {
    private static final int STRETCH = 2016;

    private final String salt;

    private int index = 0;

    public IndexStretchedHashSupplier(String salt) {
        assert salt != null;
        assert !salt.isEmpty();

        this.salt = salt;
    }

    @SneakyThrows
    @Override
    public IndexedHash get() {
        final var code = salt + index;
        var hash = MD5.hash(code).toLowerCase();

        for (var idx = 0; idx < STRETCH; idx++)
            hash = MD5.hash(hash).toLowerCase();

        return new IndexedHash(index++, hash);
    }

    @Override
    public String toString() {
        return "'stretched index hash'";
    }
}