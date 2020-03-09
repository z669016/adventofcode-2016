package com.putoet.day1;

import com.putoet.utils.ResourceLines;

import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        final List<String> directions = ResourceLines.csv("/day1.txt");
        final Location drop = new Location();
        drop.move(directions);

        System.out.println("Distance to HQ is " + drop.distance());
        System.out.println("Distance to first double is " + drop.doubles().get(0).distance());
    }
}
