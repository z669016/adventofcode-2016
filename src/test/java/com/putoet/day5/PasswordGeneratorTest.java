package com.putoet.day5;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void generatePassword() throws NoSuchAlgorithmException {
        assertEquals("18f47a30", PasswordGenerator.generatePassword("abc"));
    }

    @Test
    void generatePassword2() throws NoSuchAlgorithmException {
        assertEquals("05ace8e3", PasswordGenerator.generatePassword("abc", PasswordGenerator.GET_CHAR_7, PasswordGenerator.PUT_HASH_POS));
    }
}