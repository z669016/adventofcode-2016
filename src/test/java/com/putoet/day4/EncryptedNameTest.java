package com.putoet.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptedNameTest {

    @Test
    void from() {
        final var encryptedName = EncryptedName.of("aaaaa-bbb-z-y-x-123[abxyz]");
        assertTrue(encryptedName.isPresent());
        assertEquals("aaaaa-bbb-z-y-x", encryptedName.get().name());
        assertEquals(123, encryptedName.get().sectorId());
        assertEquals("abxyz", encryptedName.get().checksum());
        assertEquals("aaaaa-bbb-z-y-x-123[abxyz]", encryptedName.get().toString());
    }

    @Test
    void split() {
        final var triplet = EncryptedName.split("aaaaa-bbb-z-y-x-123[abxyz]");
        assertTrue(triplet.isPresent());
        assertEquals("aaaaa-bbb-z-y-x", triplet.get().getValue0());
        assertEquals(123, triplet.get().getValue1());
        assertEquals("abxyz", triplet.get().getValue2());
    }

    @Test
    void validChecksum() {
        EncryptedName.validChecksum("aaaaa-bbb-z-y-x", "abxyz");
        EncryptedName.validChecksum("a-b-c-d-e-f-g-h", "abcde");
        EncryptedName.validChecksum("not-a-real-room", "oarel");
    }

    @Test
    void
    decrypt() {
        final var name = "qzmt-zixmtkozy-ivhz";
        final var sectorId = 343;
        final var encryptedName = new EncryptedName(name, sectorId, EncryptedName.checksum(name));
        assertEquals("very encrypted name", encryptedName.decrypt());
    }
}