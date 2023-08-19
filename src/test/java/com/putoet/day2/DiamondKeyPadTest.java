package com.putoet.day2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiamondKeyPadTest {
    private KeyPad keyPad;

    @BeforeEach
    void setup() {
        keyPad = new DiamondKeyPad();
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

        assertEquals("5DB3", keyPad.code());
    }
}