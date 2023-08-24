package com.putoet.day9;

import java.util.regex.Pattern;

class Decompressor {
    private static final Pattern pattern = Pattern.compile("^(\\((\\d+)x(\\d+)\\)).*");

    public static String decompress(String compressedText) {
        final var orgLength = compressedText.length();
        final var decompressed = new StringBuilder();

        while (compressedText.contains("(")) {
            final var start = compressedText.indexOf("(");

            decompressed.append(compressedText, 0, start);
            compressedText = compressedText.substring(start);

            final var matcher = pattern.matcher(compressedText);
            if (matcher.matches()) {
                final var length = Integer.parseInt(matcher.group(2));
                var times = Integer.parseInt(matcher.group(3));

                compressedText = compressedText.substring(matcher.group(1).length());

                final var copy = compressedText.substring(0, length);
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
}
