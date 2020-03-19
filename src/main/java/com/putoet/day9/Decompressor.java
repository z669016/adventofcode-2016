package com.putoet.day9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decompressor {
    private static final Pattern pattern = Pattern.compile("^(\\((\\d+)x(\\d+)\\)).*");

    public static String decompress(String compressedText) {
        final int orgLength = compressedText.length();
        final StringBuilder decompressed = new StringBuilder();
        while (compressedText.contains("(")) {
            final int start = compressedText.indexOf("(");

            decompressed.append(compressedText.substring(0, start));
            compressedText = compressedText.substring(start);

            final Matcher matcher = pattern.matcher(compressedText);
            if (matcher.matches()) {
                final int length = Integer.parseInt(matcher.group(2));
                int times = Integer.parseInt(matcher.group(3));

                compressedText = compressedText.substring(matcher.group(1).length());

                final String copy = compressedText.substring(0, length);
                while (times-- > 0)
                    decompressed.append(copy);

                compressedText = compressedText.substring(length);
            } else {
                throw new IllegalStateException("Invalid compressed text at position " + (orgLength - compressedText.length()));
            }
        }
        decompressed.append(compressedText);

        return decompressed.toString();
    }

    public static long decompressedLength(String compressedText) {
        final int orgLength = compressedText.length();
        long decompressedLength = 0;

        while (compressedText.contains("(")) {
            final int start = compressedText.indexOf("(");

            decompressedLength += start;
            compressedText = compressedText.substring(start);

            final Matcher matcher = pattern.matcher(compressedText);
            if (matcher.matches()) {
                final int length = Integer.parseInt(matcher.group(2));
                int times = Integer.parseInt(matcher.group(3));

                compressedText = compressedText.substring(matcher.group(1).length());
                while (times-- > 0)
                    decompressedLength += length;

                compressedText = compressedText.substring(length);
            } else {
                throw new IllegalStateException("Invalid compressed text at position " + (orgLength - compressedText.length()));
            }
        }
        decompressedLength += compressedText.length();

        return decompressedLength;
    }

    public static int decompressedLengthV2(String compressedText) {
        return decompress(compressedText).length();
    }
}
