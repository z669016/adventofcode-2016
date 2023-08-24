package com.putoet.day8;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day8 {
    public static void main(String[] args) {
        final var cardReader = new CardReader(ResourceLines.list("/day8.txt"));
        final var doorLock = new DoorLock(cardReader);

        Timer.run(() -> {
            doorLock.swipe();
            System.out.println("Pixels lit is " + doorLock.pixelsLit());
        });

        Timer.run(() -> doorLock.display());
    }
}
