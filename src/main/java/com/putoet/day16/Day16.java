package com.putoet.day16;

public class Day16 {
    public static void main(String[] args) {
        final String message = "Checksum for initial state %s and fill space %d is %s%n";
        final String initialState = "01000100010010111";

        int sizeToFill = 272;
        System.out.printf(message, initialState, sizeToFill,DragonCurve.checksumForDiskSpace(initialState, sizeToFill));

        sizeToFill = 35651584;
        System.out.printf(message, initialState, sizeToFill,DragonCurve.checksumForDiskSpace(initialState, sizeToFill));
    }
}
