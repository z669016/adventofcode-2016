package com.putoet.day19;

public class WhiteElephantPartyOpposite extends WhiteElephantParty {
    public WhiteElephantPartyOpposite(int partySize) {
        super(partySize);
    }

    protected int nextFrom(int current) {
        if (active == 1) {
            return current;
        }

        int skip = active / 2;
        do {
            current = current + 1 >= elves.length ? 0 : current + 1;
            if (elves[current] != null)
                skip--;
        } while (elves[current] == null || skip > 0);

        return current;
    }
}
