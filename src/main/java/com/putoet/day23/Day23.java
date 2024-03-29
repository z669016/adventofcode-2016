package com.putoet.day23;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;
import com.putoet.utils.assembunny.Assembunny;
import com.putoet.utils.assembunny.Register;
import com.putoet.utils.assembunny.RegisterSet;

public class Day23 {
    private static Register a;
    private static Assembunny assembunny;

    public static void main(String[] args) {
        Timer.run(() -> {
            setup();
            a.accept(7);

            final var instructions = assembunny.compile(ResourceLines.list("/day23.txt"));
            assembunny.run(instructions);

            System.out.println("Part 1 - Value for register 'a' is " + a.get());
        });

        Timer.run(() -> {
            setup();
            a.accept(12);

            final var instructions = assembunny.compile(ResourceLines.list("/day23.txt"));
            assembunny.run(instructions);

            System.out.println("Part 2 - Value for register 'a' is " + a.get());
        });
    }

    private static void setup() {
        a = new Register("a");
        final var b = new Register("b");
        final var c = new Register("c");
        final var d = new Register("d");
        assembunny = new Assembunny(RegisterSet.of(a, b, c, d));
    }
}
