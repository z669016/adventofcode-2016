package com.putoet.day5;

import com.putoet.utils.MD5;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PasswordGenerator {
    private final Function<String, String> getCharacterFunction;
    private final Function<String, OptionalInt> getPositionFunction;
    private final List<String> password;

    private int index = -1;

    PasswordGenerator(int size, Function<String,String> getCharacterFunction, Function<String, OptionalInt> getPositionFunction) {
        this.getCharacterFunction = getCharacterFunction;
        this.getPositionFunction = getPositionFunction;
        this.password = emptyPassword(size);
    }

    public String generate(String prefix) {
        while (!isComplete(password)) {
            final String hash = nextMatchingHash(prefix);
            final String c = getCharacterFunction.apply(hash);
            final OptionalInt pos = getPositionFunction.apply(hash);

            // System.out.println(dump(hash, password, c, pos));
            if (pos.isPresent() && password.get(pos.getAsInt()).isEmpty())
                password.set(pos.getAsInt(), c);
        }

        return asText(password);
    }

    private String dump(String hash, List<String> password, String c, OptionalInt pos) {
        final StringBuilder sb = new StringBuilder();
        sb.append("           ").append("          1         2         3 \n");
        sb.append("           ").append("01234567890123456789012345678901\n");
        sb.append("Found hash ")
                .append(hash)
                .append(" character is ")
                .append(c)
                .append(" at position ")
                .append(pos.isPresent() ? pos.getAsInt() : ".")
                .append(" for password ")
                .append(password.stream().map(s -> s.isEmpty() ? "." : s).collect(Collectors.joining()))
                .append("\n");

        return sb.toString();
    }

    private static String asText(List<String> password) {
        return String.join("", password).toLowerCase();
    }

    private static List<String> emptyPassword(int size) {
        final List<String> emptyPassword = new ArrayList<>();
        for (int idx = 0; idx < size; idx++)
            emptyPassword.add("");

        return emptyPassword;
    }

    private static boolean isComplete(List<String> password) {
        return password.stream().noneMatch(s -> s.length() == 0);
    }

    private String nextMatchingHash(String prefix)  {
        index++;
        String key = prefix + index;
        String hash = MD5.hash(key);
        while (!hash.startsWith("00000")) {
            index++;
            key = prefix + index;
            hash = MD5.hash(key);
        }

        return hash;
    }

    public int index() { return index; }

    static final Function<String,String> GET_CHAR_6 = (String s) -> Character.toString(s.charAt(5));
    static final Function<String,OptionalInt> PUT_NEXT_POS = new Function<>() {
        int idx = 0;

        @Override
        public OptionalInt apply(String s) {
            return OptionalInt.of(idx++);
        }
    };

    static final Function<String,String> GET_CHAR_7 = hash -> Character.toString(hash.charAt(6));
    static final Function<String, OptionalInt> PUT_HASH_POS = s -> {
        int pos = s.charAt(5) - '0';
        return pos >=0 && pos <= 7 ? OptionalInt.of(pos) : OptionalInt.empty();
    };


    public static String generatePassword(String doorId) {
        final PasswordGenerator generator = new PasswordGenerator(8, GET_CHAR_6, PUT_NEXT_POS);
        return generator.generate(doorId);
    }

    public static String generatePassword(String doorId, Function<String,String> getCharacterFunction, Function<String, OptionalInt> getPositionFunction) {
        final PasswordGenerator generator = new PasswordGenerator(8, getCharacterFunction, getPositionFunction);
        return generator.generate(doorId);
    }
}
