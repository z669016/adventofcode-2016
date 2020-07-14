package com.putoet.day19;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class WhiteElephantParty {
    protected Elf[] elves;
    protected int active = 0;

    public WhiteElephantParty(int partySize) {
        assert partySize > 0;

        final Supplier<Elf> supplier = Elf.supplier();
        elves = IntStream.range(0, partySize)
                .mapToObj(i -> supplier.get())
                .toArray(Elf[]::new);
        active = partySize;
    }

    public Elf play() {
        int current = 0;
        int next = nextFrom(current);

        while (next != current) {
            elves[current].stealFrom(elves[next]);
            active--;

            current = nextCurrent(current);
            next = nextFrom(current);
        }

        return elves[current];
    }

    protected int nextCurrent(int current) {
        do {
            current = (current + 1) % elves.length;
        } while (elves[current].isSkipped());
        return current;
    }

    protected int nextFrom(int current) {
        return nextCurrent(current);
    }

    public Elf[] elves() { return elves; }

    public long activeElves() {
        return Arrays.stream(elves).filter(elf -> !elf.isSkipped()).count();
    }
}
