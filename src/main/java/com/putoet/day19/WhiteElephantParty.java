package com.putoet.day19;

import java.util.Arrays;
import java.util.Objects;
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
            // System.out.printf("%d steals from %s\n", elves[current].id(), elves[next].id());
            elves[current].stealFrom(elves[next]);
            elves[next] = null;

            active--;

            current = nextCurrent(current);
            next = nextFrom(current);
        }

        return elves[current];
    }

    protected int nextCurrent(int current) {
        do {
            current = (current + 1) >= elves.length ? 0 : current + 1;
        } while (elves[current] == null);
        return current;
    }

    protected int nextFrom(int current) {
        return nextCurrent(current);
    }

    public Elf[] elves() { return elves; }

    public long activeElves() {
        return Arrays.stream(elves).filter(Objects::nonNull).count();
    }
}
