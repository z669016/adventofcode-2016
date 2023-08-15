package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day2.txt").stream()
                .map(line -> line.chars()
                        .mapToObj(Character::toString)
                        .map(SquareKeyPad.Direction::valueOf)
                        .toList())
                .toList();

        Timer.run(() -> part("square", lines, new SquareKeyPad()));
        Timer.run(() -> part("diamond", lines, new DiamondKeyPad()));
    }

    private static void part(String type, List<List<KeyPad.Direction>> directions, KeyPad keyPad) {
        directions.forEach(action -> {
            keyPad.move(action);
            keyPad.press();
        });

        System.out.println("The access code for a " + type + " is: " + keyPad.code());
    }
}
