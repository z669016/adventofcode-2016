package com.putoet.utils;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class MD5Test {
    @Test
    void hash() throws NoSuchAlgorithmException {
        assertTrue(MD5.hash("abcdef609043").startsWith("00000"));
        assertTrue(MD5.hash("pqrstuv1048970").startsWith("00000"));

        System.out.println(MD5.hash("abcdef609043"));
    }
}