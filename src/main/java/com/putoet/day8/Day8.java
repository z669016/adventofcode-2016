package com.putoet.day8;

import com.putoet.resources.ResourceLines;

public class Day8 {
    public static void main(String[] args) {
        final CardReader cardReader = new CardReader(ResourceLines.list("/day8.txt"));
        final DoorLock doorLock = new DoorLock(cardReader);

        doorLock.swipe();

        System.out.println("Pixels lit is " + doorLock.pixelsLit());

        doorLock.display();

    }
}
