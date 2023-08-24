package com.putoet.day6;

import java.util.*;
import java.util.stream.Collectors;

class ErrorCorrect {
    public static String correct(List<String> repeatedMessages, boolean mostOften) {
        final var splitRepeatedMessages = splitMessages(repeatedMessages);
        final var characterCount = countCharacters(splitRepeatedMessages);
        final var collected = collect(characterCount, mostOften);
        return collected.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private static List<List<Character>> splitMessages(List<String> repeatedMessages) {
        return repeatedMessages.stream()
                .map(s -> s.chars().mapToObj(i -> (char) i).toList())
                .toList();
    }

    private static List<Map<Character, Integer>> countCharacters(List<List<Character>> splitRepeatedMessages) {
        final var maps = new ArrayList<Map<Character, Integer>>();
        final var size = splitRepeatedMessages.get(0).size();

        for (var idx = 0; idx < size; idx++)
            maps.add(new HashMap<>());

        splitRepeatedMessages.forEach(list -> {
            for (var idx = 0; idx < size; idx++) {
                updateMapForCharacter(maps.get(idx), list.get(idx));
            }
        });

        return maps;
    }

    private static void updateMapForCharacter(Map<Character, Integer> map, Character character) {
        map.put(character, map.getOrDefault(character, 0) + 1);
    }

    private static List<Character> collect(List<Map<Character, Integer>> characterCount, boolean mostOften) {
        return characterCount.stream()
                .map(map -> mostOften ? map.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getKey() :
                        map.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow().getKey()
                )
                .toList();
    }
}
