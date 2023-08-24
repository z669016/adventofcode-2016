package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day7 {
    public static void main(String[] args) {
        final var data = ResourceLines.list("/day7.txt");

        Timer.run(() -> System.out.println("Number of IP7's supporting TLS is " + data.stream()
                        .map(IP7::new)
                        .filter(IP7::supportsTLS)
                        .count()
                )
        );

        Timer.run(() -> System.out.println("Number of IP7's supporting SSL is " + data.stream()
                        .map(IP7::new)
                        .filter(IP7::supportSSL)
                        .count()
                )
        );
    }
}
