package com.putoet.day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IP7Test {

    @Test
    void supportsTLS() {
        assertTrue(new IP7("abba[mnop]qrst").supportsTLS());
        assertFalse(new IP7("abcd[bddb]xyyx").supportsTLS());
        assertFalse(new IP7("aaaa[qwer]tyui").supportsTLS());
        assertTrue(new IP7("ioxxoj[asdfgh]zxcvbn").supportsTLS());
    }

    @Test
    void supportsSSL() {
        assertTrue(new IP7("aba[bab]xyz").supportSSL());
        assertFalse(new IP7("xyx[xyx]xyx").supportSSL());
        assertTrue(new IP7("aaa[kek]eke").supportSSL());
        assertTrue(new IP7("zazbz[bzb]cdb").supportSSL());

        assertFalse(new IP7("abab[bzb]cdb").supportSSL());
    }

    @Test
    void outsideBrackets() {
        assertEquals(List.of("ioxxoj", "zxcvbn"), IP7.supernetSequences("ioxxoj[asdfgh]zxcvbn"));
        assertEquals(List.of("ioxxoj", "zxcvbn"), IP7.supernetSequences("ioxxoj[asdfgh][]zxcvbn"));
        assertEquals(List.of("ioxxoj", "ab", "zxcvbn"), IP7.supernetSequences("ioxxoj[asdfgh]ab[cd]zxcvbn"));
        assertEquals(List.of("ioxxoj", "ab", "zxcvbn"), IP7.supernetSequences("ioxxoj[asdfgh]ab[cd]zxcvbn[q]"));
    }

    @Test
    void betweenBrackets() {
        assertEquals(List.of("asdfgh"), IP7.hypernetSequences("ioxxoj[asdfgh]zxcvbn"));
        assertEquals(List.of("asdfgh"), IP7.hypernetSequences("[]ioxxoj[asdfgh][]zxcvbn"));
        assertEquals(List.of("asdfgh", "cd"), IP7.hypernetSequences("ioxxoj[asdfgh]ab[cd]zxcvbn"));
        assertEquals(List.of("asdfgh", "cd", "q"), IP7.hypernetSequences("ioxxoj[asdfgh]ab[cd]zxcvbn[q]"));
    }
}