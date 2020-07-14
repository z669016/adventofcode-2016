package com.putoet.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteElephantPartyTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new WhiteElephantParty(0));
    }

    @Test
    void elves() {
        final int size = 5;
        final WhiteElephantParty party = new WhiteElephantParty(size);

        assertEquals(5, party.elves().length);
        for (int idx = 0; idx < size; idx++)
            assertEquals(idx + 1, party.elves()[idx].id());
    }

    @Test
    void playNext() {
        final int size = 5;
        final WhiteElephantParty party = new WhiteElephantParty(size);
        final Elf elf = party.play();

        assertEquals(3, elf.id());
        assertEquals(5, elf.presents());
    }
}