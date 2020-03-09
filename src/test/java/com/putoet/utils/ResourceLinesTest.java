package com.putoet.utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ResourceLinesTest {

    @Test
    void stream() {
        final Stream<String> strings = ResourceLines.stream("/resourcelines.txt");
        assertEquals(15, strings.mapToInt(Integer::parseInt).sum());
    }

    @Test
    void list() {
        final List<String> lines = ResourceLines.list("/resourcelines.txt");
        assertEquals(5, lines.size());
        assertEquals(15, lines.stream().mapToInt(Integer::parseInt).sum());
    }

    @Test
    void csv() {
        final List<String> lines = ResourceLines.csv("/resourcelines.csv");
        assertEquals(List.of("1", "2", "3", "4", "5"), lines);
    }
}