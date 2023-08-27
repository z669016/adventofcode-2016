package com.putoet.day14;

import com.putoet.security.MD5;
import lombok.SneakyThrows;

import java.util.function.Supplier;

class IndexedHashSupplier implements Supplier<IndexedHash> {
    private final String salt;

    private int index = 0;

    public IndexedHashSupplier(String salt) {
        assert salt != null;
        assert !salt.isEmpty();

        this.salt = salt;
    }

    @SneakyThrows
    @Override
    public IndexedHash get() {
        final var code = salt + index;
        final var hash = MD5.hash(code).toLowerCase();
        return new IndexedHash(index++, hash);
    }

    @Override
    public String toString() {
        return "'index hash'";
    }
}
