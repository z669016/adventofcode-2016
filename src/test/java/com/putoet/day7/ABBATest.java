package com.putoet.day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ABBATest {

    @Test
    void containsABBA() {
        assertFalse(ABBA.containsABBA(""));
        assertFalse(ABBA.containsABBA("abb"));
        assertFalse(ABBA.containsABBA("aaaa"));

        assertTrue(ABBA.containsABBA("abcdioxxojzxcvbn"));
    }

    @Test
    void listABA() {
        assertTrue(ABBA.listABA("").isEmpty());
        assertTrue(ABBA.listABA("abb").isEmpty());
        assertTrue(ABBA.listABA("aaa").isEmpty());

        assertEquals(List.of("aba"), ABBA.listABA("abadioxxojzxcvbn"));
        assertEquals(List.of("bnb"), ABBA.listABA("abbdioxxojzxcvbnb"));
        assertEquals(List.of("bnb", "bab", "oxo", "bnb"), ABBA.listABA("bnbabbdioxojzxcvbnb"));
    }

    @Test
    void containsBAB() {
        assertTrue(ABBA.containsBAB(ABBA.listABA("aba[bab]xyz"), "aba[bab]xyz"));
        assertFalse(ABBA.containsBAB(ABBA.listABA("xyx[xyx]xyx"), "xyx[xyx]xyx"));
        assertTrue(ABBA.containsBAB(ABBA.listABA("zazbz[bzb]cdb"), "zazbz[bzb]cdb"));
    }

    @Test
    void bab() {
        assertThrows(AssertionError.class, () -> ABBA.bab(""));
        assertThrows(AssertionError.class, () -> ABBA.bab("1234"));

        assertEquals("bab", ABBA.bab("aba"));
    }
}