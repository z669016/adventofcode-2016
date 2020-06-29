package com.putoet.utils.assembunny;

import java.util.function.Supplier;

public class InOperant implements Supplier<Integer> {
    private final Supplier<Integer> supplier;

    public InOperant(int value) {
        supplier = () -> value;
    }

    public InOperant(Supplier<Integer> supplier) {
        assert supplier != null;
        this.supplier = supplier;
    }

    @Override
    public Integer get() {
        return supplier.get();
    }

    @Override
    public String toString() {
        return supplier instanceof Register ? ((Register) supplier).name() : String.valueOf(supplier.get());
    }
}
