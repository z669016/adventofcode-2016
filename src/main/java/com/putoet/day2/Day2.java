package com.putoet.day2;

import com.putoet.utils.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day2.txt");
        run("square", lines, new SquareKeyPad());
        run("diamond", lines, new DiamondKeyPad());
    }

    private static void run(String type, List<String> lines, KeyPad keyPad) {
        for (String line : lines) {
            keyPad.move(line.chars()
                    .mapToObj(Character::toString)
                    .map(SquareKeyPad.Direction::valueOf)
                    .collect(Collectors.toList()));
            keyPad.press();
        }

        System.out.println("The access code for a " + type + " is: " + keyPad.code());
    }
}
