package com.putoet.day10;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ChipFactoryTest {

    @Test
    void run() {
        final List<String> instructions = List.of(
                "value 5 goes to bot 2",
                "bot 2 gives low to bot 1 and high to bot 0",
                "value 3 goes to bot 1",
                "bot 1 gives low to output 1 and high to bot 0",
                "bot 0 gives low to output 2 and high to output 0",
                "value 2 goes to bot 2"
        );
        final Map<String, Consumer<Microchip>> consumers = new HashMap<>();
        final ChipFactory factory = new ChipFactory(consumers, instructions);

        factory.run();

        final List<Output> outputs = Output.from(consumers);
        assertEquals(3, outputs.size());
        for (int idx = 0; idx < 3; idx++) {
            final Output output = outputs.get(idx);
            if (output.name().equals("output-0"))
                assertEquals(List.of(new Microchip(5)), output.list());
            else if (output.name().equals("output-1"))
                assertEquals(List.of(new Microchip(2)), output.list());
            else if (output.name().equals("output-2"))
                assertEquals(List.of(new Microchip(3)), output.list());
            else
                fail();
        }

        final List<Bot> bots = Bot.from(consumers);
        assertEquals(3, bots.size());
        bots.forEach(bot -> assertEquals(2, bot.microchips().size()));

    }
}