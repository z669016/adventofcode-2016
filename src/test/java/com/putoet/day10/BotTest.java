package com.putoet.day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BotTest {
    private Bot bot;

    @BeforeEach
    void setup() {
        bot = new Bot(13);
        bot.accept(new Microchip(5));
        bot.accept(new Microchip(9));
    }

    @Test
    void name() {
        assertEquals("bot-13", bot.name());
    }

    @SuppressWarnings("unchecked")
    @Test
    void receive() {
        assertThrows(IllegalStateException.class, () -> bot.accept(new Microchip(3)));

        final var lower = (Consumer<Microchip>) mock(Consumer.class);
        final var higher =  (Consumer<Microchip>) mock(Consumer.class);

        bot.setLowerConsumer(lower);
        bot.setHigherConsumer(higher);

        verify(lower).accept(new Microchip(5));
        verify(higher).accept(new Microchip(9));
    }
}