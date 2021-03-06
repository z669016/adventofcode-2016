package com.putoet.day4;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EncryptedName {
    private static final Pattern PATTERN = Pattern.compile("([a-z\\-]+)-([0-9)]+)\\[([a-z]{5})]");
    private static final int CHECKSUM_SIZE = 5;

    private final String name;
    private final int sectorId;
    private final String checksum;

    public EncryptedName(String name, int sectorId, String checksum) {
        assert name != null;
        assert name.chars().distinct().count() >= 5;
        assert checksum != null;
        assert checksum.length() == 5;

        this.name = name;
        this.sectorId = sectorId;
        this.checksum = checksum;
    }

    public String name() { return name; }
    public int sectorId() { return sectorId; }
    public String checksum() { return checksum; }

    public String decrypt() {
        return decrypt(name, sectorId);
    }

    @Override
    public String toString() {
        return name + "-" + sectorId + "[" + checksum + "]";
    }

    public static Optional<EncryptedName> from(String encryptedName) {
        final Optional<Triplet<String,Integer,String>> triplet = split(encryptedName);
        if (triplet.isPresent()) {
            final String name = triplet.get().getValue0();
            final Integer sectorId = triplet.get().getValue1();
            final String checksum = triplet.get().getValue2();

            if (validChecksum(name, checksum)) {
                return Optional.of(new EncryptedName(name, sectorId, checksum));
            }
        }
        return Optional.empty();
    }

    static boolean validChecksum(String name, String checksum) {
        assert name != null;
        assert checksum != null;

        return checksum(name).equals(checksum);
    }

    private static List<Pair<Long,String>> letterList(String name) {
        final String letters = name.replaceAll("-", "");

        return letters.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> Pair.with(entry.getValue(), entry.getKey()))
                .sorted((o1, o2) -> (!o1.getValue0().equals(o2.getValue0()) ? Long.compare(o2.getValue0(), o1.getValue0()) : o1.getValue1().compareTo(o2.getValue1())))
                .collect(Collectors.toList());
    }

    public static String checksum(String name) {
        final String letters = name.replaceAll("-", "");

        return letterList(name).stream()
                .limit(CHECKSUM_SIZE)
                .map(Pair::getValue1)
                .collect(Collectors.joining());
    }

    static Optional<Triplet<String, Integer, String>> split(String encryptedName) {
        final Matcher matcher = PATTERN.matcher(encryptedName);
        if (matcher.matches()) {
            return Optional.of(Triplet.with(matcher.group(1), Integer.parseInt(matcher.group(2)), matcher.group(3)));
        }

        return Optional.empty();
    }

    private static String decrypt(String name, int sectorId) {
        return name.chars().mapToObj(i -> decrypt(i, sectorId)).collect(Collectors.joining());
    }

    private static String decrypt(int i, int sectorId) {
        if (i == '-')
            return " ";

        i = i + (sectorId % 26);
        return Character.toString(i  > 'z' ? i - 26 : i);
    }


}
