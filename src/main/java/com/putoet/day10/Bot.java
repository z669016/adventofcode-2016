package com.putoet.day10;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Bot implements Consumer<Microchip> {
    public static final String PREFIX = "bot-";

    private final int id;
    private final Set<Microchip> microchips = new HashSet<>();
    private Optional<Consumer<Microchip>> higherConsumer = Optional.empty();
    private Optional<Consumer<Microchip>> lowerConsumer = Optional.empty();

    public Bot(int id) {
        this.id = id;
    }

    public String name() { return PREFIX + id; }

    public Set<Microchip> microchips() { return microchips; }

    public boolean contains(final Microchip chip) {
        return microchips.contains(chip);
    }

    public void setHigherConsumer(Consumer<Microchip> higherConsumer) {
        assert higherConsumer != null;

        this.higherConsumer = Optional.of(higherConsumer);
        distribute();
    }

    public void setLowerConsumer(Consumer<Microchip> lowerConsumer) {
        assert lowerConsumer != null;

        this.lowerConsumer = Optional.of(lowerConsumer);
        distribute();
    }

    private Optional<Microchip> higher() {
        return microchips.stream().max(Microchip::compareTo);
    }

    private Optional<Microchip> lower() {
        return microchips.stream().min(Microchip::compareTo);
    }

    @Override
    public void accept(Microchip microchip) {
        if (microchips.size() > 1 )
            throw new IllegalStateException(name() + " cannot accept microchip " + microchip + " for it is holding already " + microchips);

        microchips.add(microchip);
        distribute();
    }

    private void distribute() {
        if ((microchips.size() == 2) && higherConsumer.isPresent() && lowerConsumer.isPresent()) {
            final Optional<Microchip> higher = higher();
            final Optional<Microchip> lower = lower();

            higher.ifPresent(higherConsumer.get());
            lower.ifPresent(lowerConsumer.get());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bot bot)) return false;
        return id == bot.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bot{" +
                "id=" + id +
                ", microchips=" + microchips +
                '}';
    }
}
