package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DfParserTest {
    private final DfParser parser = new DfParser();

    @Test
    void patternInvalid() {
        final var node = parser.apply("root@ebhq-gridcenter# df -h");
        assertFalse(node.isPresent());
    }

    @Test
    void pattern() {
        final var node = parser.apply("/dev/grid/node-x0-y0     92T   68T    24T   73%");
        assertTrue(node.isPresent());
        assertEquals("/dev/grid/node-x0-y0", node.get().name());
        assertEquals(92, node.get().size());
        assertEquals(68, node.get().used());
    }

    @Test
    void parse() {
        final var lines = List.of(
                "/dev/grid/node-x0-y0     92T   68T    24T   73%",
                "/dev/grid/node-x0-y1     87T   73T    14T   83%",
                "/dev/grid/node-x0-y2     89T   64T    25T   71%",
                "/dev/grid/node-x0-y3     91T   64T    27T   70%"
        );
        final var nodes = DfParser.parse(lines);

        assertEquals(lines.size(), nodes.size());
    }
}