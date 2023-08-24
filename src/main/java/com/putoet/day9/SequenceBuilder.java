package com.putoet.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class SequenceBuilder implements Sequence {
    private final List<Sequence> sequences;

    private SequenceBuilder(List<Sequence> sequences) {
        this.sequences = sequences;
    }

    @Override
    public long length() {
        return sequences.stream().mapToLong(Sequence::length).sum();
    }

    @Override
    public String text() {
        return sequences.stream().map(Sequence::text).collect(Collectors.joining());
    }

    public static Sequence from(String line) {
        final var orgLength = line.length();
        final var sequences = new ArrayList<Sequence>();

        while (line.contains("(")) {
            final var start = line.indexOf("(");

            sequences.add(new FixedSequence(line.substring(0, start)));

            line = line.substring(start);
            final var matcher = Sequence.REPEAT_SEQUENCE.matcher(line);
            if (matcher.matches()) {
                final var length = matcher.group(1).length() + Integer.parseInt(matcher.group(2));
                sequences.add(new RepeatSequence(line.substring(0, length)));
                line = line.substring(length);
            } else {
                throw new IllegalStateException("Invalid compressed text at position " + (orgLength - line.length()));
            }
        }
        sequences.add(new FixedSequence(line));

        return sequences.size() > 1 ? new SequenceBuilder(sequences) : sequences.get(0);
    }

    @Override
    public String toString() {
        return sequences.stream().map(Object::toString).collect(Collectors.joining());
    }

}
