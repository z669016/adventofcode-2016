package com.putoet.day10;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChipFactory implements Runnable {
    private enum DestinationType {
        bot, output
    }

    private static final Pattern VALUE_PATTERN = Pattern.compile("value (\\d+) goes to bot (\\d+)");
    private static final Pattern GIVES_PATTERN = Pattern.compile("bot (\\d+) gives low to (output|bot) (\\d+) and high to (output|bot) (\\d+)");

    private final Map<String,Consumer<Microchip>> consumers;
    private final Iterator<String> iterator;

    public ChipFactory(List<String> instructions) {
        assert instructions != null;

        this.consumers = new HashMap<>();
        this.iterator = instructions.iterator();
    }

    public List<Bot> bots() {
        return consumers.values().stream()
                .filter(consumer -> consumer instanceof Bot)
                .map(consumer -> (Bot) consumer)
                .toList();
    }

    public List<Output> output() {
        return consumers.values().stream()
                .filter(consumer -> consumer instanceof Output)
                .map(consumer -> (Output) consumer)
                .toList();
    }
    @Override
    public void run() {
        while (iterator.hasNext()) {
            final String instruction = iterator.next();
            final Matcher valueMatcher = VALUE_PATTERN.matcher(instruction);
            final Matcher givesMatcher = GIVES_PATTERN.matcher(instruction);

            if (givesMatcher.matches()) {
                final Bot bot = findOrCreateBot(asInt(givesMatcher.group(1)));
                final DestinationType lowerType = DestinationType.valueOf(givesMatcher.group(2));
                final Consumer<Microchip> lowerConsumer = findOrCreateConsumer(asInt(givesMatcher.group(3)), lowerType);
                final DestinationType higherType = DestinationType.valueOf(givesMatcher.group(4));
                final Consumer<Microchip> higherConsumer = findOrCreateConsumer(asInt(givesMatcher.group(5)), higherType);

                bot.setHigherConsumer(higherConsumer);
                bot.setLowerConsumer(lowerConsumer);
            } else if (valueMatcher.matches()){
                final Microchip microchip = new Microchip(asInt(valueMatcher.group(1)));
                final Bot bot = findOrCreateBot(asInt(valueMatcher.group(2)));

                bot.accept(microchip);
            } else {
                throw new IllegalArgumentException("Invalid instruction '" + instruction + "'");
            }
        }
    }

    private int asInt(String group) {
        return Integer.parseInt(group);
    }

    private Bot findOrCreateBot(int id) {
        return (Bot) findOrCreateConsumer(id, DestinationType.bot);
    }

    private Consumer<Microchip> findOrCreateConsumer(int id, DestinationType destinationType) {
        if (destinationType == DestinationType.bot)
            return findOrCreateConsumer(Bot.PREFIX + id, () -> new Bot(id));

        return findOrCreateConsumer(Output.PREFIX + id, () -> new Output(id));
    }

    private Consumer<Microchip> findOrCreateConsumer(String id, Supplier<Consumer<Microchip>> supplier) {
        if (!consumers.containsKey(id))
            consumers.put(id, supplier.get());

        return consumers.get(id);
    }
}
