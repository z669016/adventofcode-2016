package com.putoet.day8;

import com.putoet.utils.ResourceLines;

public class Day8 {
    public static void main(String[] args) {
        final TinyCodeDisplayingScreen display = new TinyCodeDisplayingScreen();
        final CardReader cardReader = new CardReader(ResourceLines.list("/day8.txt"));
        final DoorLock doorLock = new DoorLock(display, cardReader);

        doorLock.swipe();
        doorLock.display();

        System.out.println("Pixels lit is " + doorLock.pixelsLit());

        // zfhsfogpo is not the right answer
        // zfhfsfogpo is also no the right answer
    }
}
