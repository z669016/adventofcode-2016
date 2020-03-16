package com.putoet.day6;

import com.putoet.utils.ResourceLines;

import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        final List<String> data = ResourceLines.list("/day6.txt");
        assert data.size() == 572;

        System.out.println("The corrected error message is: " + ErrorCorrect.correct(data, true));
        System.out.println("The corrected (min) error message is: " + ErrorCorrect.correct(data, false));
    }
}
