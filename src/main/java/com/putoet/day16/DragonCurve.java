package com.putoet.day16;

public class DragonCurve {
    public static String checksumForDiskSpace(String initialState, int sizeToFill) {
        while (initialState.length() < sizeToFill)
            initialState = grow(initialState);

        return checksum(initialState, sizeToFill);
    }

    public static String grow(String binary) {
        assert binary != null;
        assert binary.matches("[0-1]+");

        final String a = binary;
        final String b = transform(a);
        return a + "0" + b;
    }

    private static String transform(String a) {
        String b = new StringBuilder(a).reverse().toString();
        return b.chars()
                .map(c -> c == '1' ? '0' : '1')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String checksum(String binary, int length) {
        assert binary != null;
        assert binary.length() >= length;
        assert length % 2 == 0;

        return checksum(binary.substring(0, length));
    }

    private static String checksum(String binary) {
        final String checksum = calculateChecksum(binary);

        return checksum.length() % 2 == 1 ? checksum : checksum(checksum);
    }

    private static String calculateChecksum(String binary) {
        if (binary.length() % 2 == 1)
            throw new IllegalArgumentException("Cannot calculate checksum on odd length string '" + binary + "'");

        final StringBuilder sb = new StringBuilder();
        final char[] chars = binary.toCharArray();
        for (int i = 0; i < binary.length(); i += 2)
            sb.append(chars[i] == chars[i+1] ? "1" : "0");

        return sb.toString();
    }
}
