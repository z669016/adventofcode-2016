package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day6 {
    public static void main(String[] args) {
        final var data = ResourceLines.list("/day6.txt");
        assert data.size() == 572;

        Timer.run(() -> System.out.println("The corrected error message is: " + ErrorCorrect.correct(data, true)));
        Timer.run(() -> System.out.println("The corrected (min) error message is: " + ErrorCorrect.correct(data, false)));
    }
}
