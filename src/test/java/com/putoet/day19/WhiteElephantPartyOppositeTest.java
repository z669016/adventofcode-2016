package com.putoet.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteElephantPartyOppositeTest {
    @Test
    void playOpposite() {
        final int size = 5;
        final WhiteElephantPartyOpposite party = new WhiteElephantPartyOpposite(size);
        final Elf elf = party.play();

        assertEquals(1, party.activeElves());
        assertEquals(2, elf.id());
        assertEquals(5, elf.presents());
    }
}