package com.putoet.day5;

import com.putoet.security.MD5;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;

class PasswordGenerator {
    private final Function<String, String> getCharacterFunction;
    private final Function<String, OptionalInt> getPositionFunction;
    private final List<String> password;

    private int index = -1;

    PasswordGenerator(int size,
                      Function<String, String> getCharacterFunction,
                      Function<String, OptionalInt> getPositionFunction) {
        this.getCharacterFunction = getCharacterFunction;
        this.getPositionFunction = getPositionFunction;
        this.password = emptyPassword(size);
    }

    public String generate(String prefix) {
        while (!isComplete(password)) {
            final var hash = nextMatchingHash(prefix);
            final var c = getCharacterFunction.apply(hash);
            final var pos = getPositionFunction.apply(hash);

            // System.out.println(dump(hash, password, c, pos));
            if (pos.isPresent() && password.get(pos.getAsInt()).isEmpty())
                password.set(pos.getAsInt(), c);
        }

        return asText(password);
    }

    private static String asText(List<String> password) {
        return String.join("", password).toLowerCase();
    }

    private static List<String> emptyPassword(int size) {
        return new ArrayList<>(Collections.nCopies(size, ""));
    }

    private static boolean isComplete(List<String> password) {
        return password.stream().noneMatch(String::isEmpty);
    }

    @SneakyThrows
    private String nextMatchingHash(String prefix) {
        index++;
        var key = prefix + index;
        var hash = MD5.hash(key);
        while (!hash.startsWith("00000")) {
            index++;
            key = prefix + index;
            hash = MD5.hash(key);
        }

        return hash;
    }

    public int index() {
        return index;
    }

    static final Function<String, String> GET_CHAR_6 = (String s) -> Character.toString(s.charAt(5));

    static final Function<String, OptionalInt> PUT_NEXT_POS = new Function<>() {
        int idx = 0;

        @Override
        public OptionalInt apply(String s) {
            return OptionalInt.of(idx++);
        }
    };

    static final Function<String, String> GET_CHAR_7 = hash -> Character.toString(hash.charAt(6));

    static final Function<String, OptionalInt> PUT_HASH_POS = s -> {
        int pos = s.charAt(5) - '0';
        return pos >= 0 && pos <= 7 ? OptionalInt.of(pos) : OptionalInt.empty();
    };


    public static String generatePassword(String doorId) {
        final var generator = new PasswordGenerator(8, GET_CHAR_6, PUT_NEXT_POS);
        return generator.generate(doorId);
    }

    public static String generatePassword(String doorId,
                                          Function<String, String> getCharacterFunction,
                                          Function<String, OptionalInt> getPositionFunction) {
        final var generator = new PasswordGenerator(8, getCharacterFunction, getPositionFunction);
        return generator.generate(doorId);
    }
}
