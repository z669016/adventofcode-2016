package com.putoet.day5;

import com.putoet.utils.Timer;

public class Day5 {
    private static final String DAY5_INPUT = "abbhdwsy";

    public static void main(String[] args) {
        Timer.run(() ->
                System.out.println("Password for '" + DAY5_INPUT + "' is " +
                                   PasswordGenerator.generatePassword(DAY5_INPUT))
        );

        Timer.run(() ->
                System.out.println("Safer password for '" + DAY5_INPUT + "' is " +
                                   PasswordGenerator.generatePassword(DAY5_INPUT, PasswordGenerator.GET_CHAR_7, PasswordGenerator.PUT_HASH_POS))
        );
    }
}
