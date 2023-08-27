package com.putoet.day16;

import com.putoet.utils.Timer;

public class Day16 {
    public static void main(String[] args) {
        final var message = "Checksum for initial state %s and fill space %d is %s%n";
        final var initialState = "01000100010010111";

        Timer.run(() -> {
            var sizeToFill = 272;
            System.out.printf(message, initialState, sizeToFill, DragonCurve.checksumForDiskSpace(initialState, sizeToFill));
        });

        Timer.run(() -> {
            var sizeToFill = 35651584;
            System.out.printf(message, initialState, sizeToFill, DragonCurve.checksumForDiskSpace(initialState, sizeToFill));
        });
    }
}
