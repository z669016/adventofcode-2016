package com.putoet.day6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorCorrectTest {

    @Test
    void correct() {
        final var repeatedMessages = List.of("eedadn",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar");
        assertEquals("easter", ErrorCorrect.correct(repeatedMessages, true));
        assertEquals("advent", ErrorCorrect.correct(repeatedMessages, false));
    }
}