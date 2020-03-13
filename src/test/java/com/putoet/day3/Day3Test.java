package com.putoet.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    void transformList() {
        final List<String> list = List.of(
                "101 301 501",
                " 102 302 502",
                "103 303  503",
                "201  401 601",
                "202 402 602 ",
                "   203   403   603  "
        );
        final List<List<Integer>> transformed = List.of(
                List.of(101, 102, 103),
                List.of(301, 302, 303),
                List.of(501, 502, 503),
                List.of(201, 202, 203),
                List.of(401, 402, 403),
                List.of(601, 602, 603)
        );

        assertEquals(transformed, Day3.transformList(list));
    }
}