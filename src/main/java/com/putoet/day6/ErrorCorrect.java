package com.putoet.day6;

import java.util.*;
import java.util.stream.Collectors;

public class ErrorCorrect {
    public static String correct(List<String> repeatedMessages, boolean mostOften) {
        final List<List<String>> splitRepeatedMessages = splitMessages(repeatedMessages);
        final List<Map<String,Integer>> characterCount = countCharacters(splitRepeatedMessages);
        final List<String> collected = collect(characterCount, mostOften);
        return String.join("", collected);
    }

    private static List<List<String>> splitMessages(List<String> repeatedMessages) {
        return repeatedMessages.stream()
                .map(s -> s.chars().mapToObj(Character::toString).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static List<Map<String,Integer>> countCharacters(List<List<String>> splitRepeatedMessages) {
        final List<Map<String,Integer>> maps = new ArrayList<>();
        final int size = splitRepeatedMessages.get(0).size();

        for (int idx = 0; idx < size; idx++)
            maps.add(new HashMap<>());

        splitRepeatedMessages.forEach(list -> {
            for (int idx = 0; idx < size; idx++) {
                updateMapForCharacter(maps.get(idx), list.get(idx));
            }
        });

        return maps;
    }

    private static List<String> collect(List<Map<String,Integer>> characterCount, boolean mostOften) {
        if (mostOften)
            return characterCount.stream()
                    .map(map -> map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey())
                    .collect(Collectors.toList());

        return characterCount.stream()
                .map(map -> map.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey())
                .collect(Collectors.toList());

    }

    private static void updateMapForCharacter(Map<String, Integer> map, String character) {
        map.put(character, map.getOrDefault(character, 0) + 1);
    }
}
