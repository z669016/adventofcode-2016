package com.putoet.day19;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WhiteElephantParty {
    protected List<Elf> elves;
    protected int active = 0;

    public WhiteElephantParty(int partySize) {
        assert partySize > 0;

        final Supplier<Elf> supplier = Elf.supplier();
        elves = IntStream.range(0, partySize)
                .mapToObj(i -> supplier.get())
                .collect(Collectors.toCollection(LinkedList::new));
        active = partySize;
    }

    public Elf play() {
        int current = 0;
        int next = nextFrom(current);

        while (next != current) {
            elves.get(current).stealFrom(elves.get(next));
            elves.remove(next);
            active--;

            current = nextCurrent(current);
            next = nextFrom(current);
        }

        return elves.get(current);
    }

    protected int nextCurrent(int current) {
        return current >= elves.size() ? 0 : (current + 1) % elves.size();
    }

    protected int nextFrom(int current) {
        return nextCurrent(current);
    }

    public List<Elf> elves() { return elves; }

    public long activeElves() {
        return elves.stream().filter(elf -> !elf.isSkipped()).count();
    }
}
