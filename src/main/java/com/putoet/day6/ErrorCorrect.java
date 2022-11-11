package com.putoet.day6;

import java.util.*;
import java.util.stream.Collectors;

public class ErrorCorrect {
    public static String correct(List<String> repeatedMessages, boolean mostOften) {
        final List<List<Character>> splitRepeatedMessages = splitMessages(repeatedMessages);
        final List<Map<Character,Integer>> characterCount = countCharacters(splitRepeatedMessages);
        final List<Character> collected = collect(characterCount, mostOften);
        return collected.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private static List<List<Character>> splitMessages(List<String> repeatedMessages) {
        return repeatedMessages.stream()
                .map(s -> s.chars().mapToObj(i -> (char) i).toList())
                .toList();
    }

    private static List<Map<Character,Integer>> countCharacters(List<List<Character>> splitRepeatedMessages) {
        final List<Map<Character,Integer>> maps = new ArrayList<>();
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

    private static void updateMapForCharacter(Map<Character, Integer> map, Character character) {
        map.put(character, map.getOrDefault(character, 0) + 1);
    }

    private static List<Character> collect(List<Map<Character,Integer>> characterCount, boolean mostOften) {
        if (mostOften)
            return characterCount.stream()
                    .map(map -> map.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getKey())
                    .toList();

        return characterCount.stream()
                .map(map -> map.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow().getKey())
                .toList();

    }
}
