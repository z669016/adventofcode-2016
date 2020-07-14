package com.putoet.day19;

public class WhiteElephantPartyOpposite extends WhiteElephantParty {
    public WhiteElephantPartyOpposite(int partySize) {
        super(partySize);
    }

    protected int nextFrom(int current) {
        if (active == 1)
            return current;

        int skip = (active - 1) / 2;
        do {
            current = (current + 1) % elves.length;
            if (!elves[current].isSkipped())
                skip--;
        } while (elves[current].isSkipped() || skip > 0);

        return current;
    }
}
