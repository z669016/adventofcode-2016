package com.putoet.day10;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        final var instructions = ResourceLines.list("/day10.txt");
        final var factory = new ChipFactory(instructions);

        Timer.run(() -> {
            factory.run();

            final var bots = factory.bots();
            final var bot = bots.stream()
                    .filter(b -> b.contains(new Microchip(17)) && b.contains(new Microchip(61)))
                    .findAny()
                    .orElseThrow();

            System.out.println("The bot managing chip 17 and 71 is " + bot.name());
        });

        Timer.run(() ->
                System.out.println("Multiplication of the outputs 0, 1, 2: " +
                                   factory.output().stream()
                                           .filter(output -> output.id() < 3)
                                           .map(Output::list)
                                           .flatMap(Collection::stream)
                                           .mapToInt(Microchip::value)
                                           .reduce(1, (a, b) -> a * b)
                )
        );
    }
}
