package com.putoet.day2;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day2.txt");
        run("square", lines, new SquareKeyPad());
        run("diamond", lines, new DiamondKeyPad());
    }

    private static void run(String type, List<String> lines, KeyPad keyPad) {
        for (String line : lines) {
            final var directions = line.chars()
                    .mapToObj(Character::toString)
                    .map(SquareKeyPad.Direction::valueOf)
                    .toList();

            keyPad.move(directions);
            keyPad.press();
        }

        System.out.println("The access code for a " + type + " is: " + keyPad.code());
    }
}
