package com.putoet.day2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SquareKeyPadTest {
    private SquareKeyPad keyPad;

    @BeforeEach
    void setup() {
        keyPad = new SquareKeyPad();
    }

    @Test
    void move() {
        final List<String> list = List.of("ULL", "RRDDD", "LURDL", "UUUUD");
        for (var line : list) {
            keyPad.move(line.chars()
                    .mapToObj(Character::toString)
                    .map(SquareKeyPad.Direction::valueOf)
                    .collect(Collectors.toList()));
            keyPad.press();
        }

        assertEquals("1985", keyPad.code());
    }

    @Test
    void press() {
        assertEquals(List.of(), keyPad.codeList());
        keyPad.press();
        keyPad.press();
        assertEquals(List.of(5, 5), keyPad.codeList());
        assertEquals("55", keyPad.code());
    }
}