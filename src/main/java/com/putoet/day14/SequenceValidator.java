package com.putoet.day14;

import java.util.Optional;

public class SequenceValidator {
    public static Optional<String> triplet(String text) {
        assert text != null;

        if (text.length() < 3)
            return Optional.empty();

        char[] chars = text.toCharArray();
        for (int idx = 0; idx < chars.length - 2; idx++)
            if (chars[idx] == chars[idx+1] && chars[idx] == chars[idx+2])
                return Optional.of(String.valueOf(chars[idx]));

        return Optional.empty();
    }

    public static boolean fivelet(String triplet, String text) {
        assert triplet != null && triplet.length() == 1;
        assert text != null;

        return text.contains(triplet.repeat(5));
    }
}
