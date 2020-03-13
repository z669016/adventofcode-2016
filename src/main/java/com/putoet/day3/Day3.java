package com.putoet.day3;

import com.putoet.utils.ResourceLines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) {
        final List<String> list = ResourceLines.list("/day3.txt");
        System.out.println("Possible triangle count is " + list.stream()
                .map(Figure::from)
                .filter(Figure::possibleTriangle)
                .count());

        System.out.println("Possible triangle count on the transformed list is " + transformList(list).stream()
                .map(Figure::from)
                .filter(Figure::possibleTriangle)
                .count());
    }

    public static List<List<Integer>> transformList(List<String> list) {
        final List<List<Integer>> lists = new ArrayList<>();
        int idx = 0;
        while(idx < list.size()) {
            final List<Integer> listOne = transformString(list.get(idx++));
            final List<Integer> listTwo = transformString(list.get(idx++));
            final List<Integer> listThree = transformString(list.get(idx++));

            lists.add(List.of(listOne.get(0), listTwo.get(0), listThree.get(0)));
            lists.add(List.of(listOne.get(1), listTwo.get(1), listThree.get(1)));
            lists.add(List.of(listOne.get(2), listTwo.get(2), listThree.get(2)));
        }

        return lists;
    }

    private static List<Integer> transformString(String line) {
        line = line.trim();
        while (line.contains("  "))
            line = line.replace("  ", " ");
        return Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
