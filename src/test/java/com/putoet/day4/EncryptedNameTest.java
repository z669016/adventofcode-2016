package com.putoet.day4;

import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncryptedNameTest {

    @Test
    void from() {
        final Optional<EncryptedName> encryptedName = EncryptedName.from("aaaaa-bbb-z-y-x-123[abxyz]");
        assertTrue(encryptedName.isPresent());
        assertEquals("aaaaa-bbb-z-y-x", encryptedName.get().name());
        assertEquals(123, encryptedName.get().sectorId());
        assertEquals("abxyz", encryptedName.get().checksum());
        assertEquals("aaaaa-bbb-z-y-x-123[abxyz]", encryptedName.get().toString());
    }

    @Test
    void split() {
        final Optional<Triplet<String,Integer,String>> triplet = EncryptedName.split("aaaaa-bbb-z-y-x-123[abxyz]");
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
        final String name = "qzmt-zixmtkozy-ivhz";
        final int sectorId = 343;
        final EncryptedName encryptedName = new EncryptedName(name, sectorId, EncryptedName.checksum(name));
        assertEquals("very encrypted name", encryptedName.decrypt());
    }
}