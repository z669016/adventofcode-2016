package com.putoet.day22;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

public class DfParser implements Function<String, Optional<Node>> {
    public static final String PREFIX = "/dev/grid/node";
    public static final String SEPARATOR = "-";
    private static final Pattern PATTERN = Pattern.compile(PREFIX + SEPARATOR + "x([0-9]+)" + SEPARATOR + "y([0-9]+) +([0-9]+)T +([0-9]+)T +([0-9]+)T +([0-9]+)%");

    @Override
    public Optional<Node> apply(String line) {
        final var matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            return Optional.empty();

        final var x = Integer.parseInt(matcher.group(1));
        final var y = Integer.parseInt(matcher.group(2));
        final var size = Integer.parseInt(matcher.group(3));
        final var used = Integer.parseInt(matcher.group(4));
        final var free = Integer.parseInt(matcher.group(5));
        final var use = Integer.parseInt(matcher.group(6));

        final var node = new Node(x, y, size, used);

        if (free != node.free())
            throw new IllegalArgumentException(node + " free space is invalid. Expected " + free + " but was " + node.free());
        if (use != node.use())
            throw new IllegalArgumentException(node + " use space is invalid. Expected " + use + " but was " + node.use());

        return Optional.of(node);
    }

    public static List<Node> parse(List<String> lines) {
        final var parser = new DfParser();

        return lines.stream()
                .map(parser)
                .flatMap(Optional::stream)
                .toList();
    }
}
