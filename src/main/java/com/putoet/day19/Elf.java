package com.putoet.day19;

import java.util.function.Supplier;

public class Elf {
    private static class ElfSupplier implements Supplier<Elf> {
        private int id = 1;

        @Override
        public Elf get() {
            return new Elf(id++);
        }
    }

    public static Supplier<Elf> supplier() {
        return new ElfSupplier();
    }

    private final int id;
    private int presents = 1;

    private Elf(int id) {
        this.id = id;
    }

    public int id() { return id; }
    public int presents() { return presents; }

    public boolean isSkipped() {
        return presents == 0;
    }

    public void stealFrom(Elf elf) {
        assert elf != null;

        this.presents += elf.presents;
        elf.presents = 0;
    }
}
