package com.putoet.day9;

class RepeatSequence implements Sequence {
    private final int length;
    private final int times;

    private final Sequence sequence;

    public RepeatSequence(String text) {
        final var matcher = Sequence.REPEAT_SEQUENCE.matcher(text);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid repeat definition: '" + text + "'");

        this.length = Integer.parseInt(matcher.group(2));
        this.times = Integer.parseInt(matcher.group(3));

        text = matcher.group(4);
        sequence = SequenceBuilder.from(text);
    }

    @Override
    public long length() {
        return times * sequence.length();
    }

    @Override
    public String text() {
        final var sb = new StringBuilder();
        for (var idx = 0; idx < times; idx++)
            sb.append(sequence.text());

        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("(%dx%d)", length, times) + sequence.toString();
    }

}
