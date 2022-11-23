package com.putoet.day20;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Range(long lowerBound, long upperBound) implements Comparable<Range> {
    private static final Pattern PATTERN = Pattern.compile("([0-9]+)-([0-9]+)");

    public static Range of(String line) {
        assert line != null;
        assert line.length() > 3;

        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException(("Invalid range '" + line + "'"));

        return Range.of(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2)));
    }

    public static Range of(long lowerBound, long upperBound) {
        assert lowerBound >= 0;
        assert upperBound >= lowerBound;

        return new Range(lowerBound, upperBound);
    }

    public boolean overlaps(Range other) {
        return this.lowerBound >= other.lowerBound && this.lowerBound <= other.upperBound
                || other.lowerBound >= this.lowerBound && other.lowerBound <= this.upperBound
                || this.upperBound >= other.lowerBound && this.upperBound <= other.upperBound
                || other.upperBound >= this.lowerBound && other.upperBound <= this.upperBound
                || this.lowerBound == other.upperBound + 1
                || this.upperBound == other.lowerBound - 1;
    }

    public Range merge(Range other) {
        if (!overlaps(other))
            throw new IllegalArgumentException(String.format("Ranges %s and %s don't overlap", this, other));

        return new Range(Math.min(this.lowerBound, other.lowerBound), Math.max(this.upperBound, other.upperBound));
    }

    @Override
    public String toString() {
        return lowerBound + "-" + upperBound;
    }

    @Override
    public int compareTo(Range other) {
        if (equals(other))
            return 0;

        if (lowerBound == other.lowerBound)
            return other.upperBound < upperBound ? 1 : -1;

        return other.lowerBound < lowerBound ? 1 : -1;
    }
}
