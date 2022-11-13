package com.putoet.day10;

import com.putoet.resources.ResourceLines;

import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        final List<String> instructions = ResourceLines.list("/day10.txt");
        final ChipFactory factory = new ChipFactory(instructions);

        factory.run();

        final List<Bot> bots = factory.bots();
        final Optional<Bot> bot = bots.stream()
                .filter(b -> b.contains(new Microchip(17)) && b.contains(new Microchip(61)))
                .findAny();

        if (bot.isPresent()) {
            System.out.println("The bot managing chip 17 and 71 is " + bot.get().name());
        } else {
            System.out.println("No bot found which managed chip 17 and 71.");
        }

        final List<Output> outputs = factory.output();
        System.out.println("Multiplication of the outputs 0, 1, 2: " +
                outputs.stream()
                        .filter(output -> output.id() < 3)
                        .map(Output::list)
                        .flatMap(Collection::stream)
                        .mapToInt(Microchip::value)
                        .reduce(1, (a, b) -> a * b)
        );
    }
}
