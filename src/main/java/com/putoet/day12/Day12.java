package com.putoet.day12;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.assembunny.Assembunny;
import com.putoet.utils.assembunny.Instruction;
import com.putoet.utils.assembunny.Register;
import com.putoet.utils.assembunny.RegisterSet;

public class Day12 {
    private static Register a, c;
    private static Assembunny assembunny;

    public static void main(String[] args) {
        setup();

        Instruction[] instructions = assembunny.compile(ResourceLines.list("/day12.txt"));
        assembunny.run(instructions);
        System.out.println("Part 1 - Value for register 'a' is " + a.get());

        setup();
        c.accept(1);
        instructions = assembunny.compile(ResourceLines.list("/day12.txt"));
        assembunny.run(instructions);
        System.out.println("Part 2 - Value for register 'a' is " + a.get());
    }

    private static void setup() {
        final Register b = new Register("b");
        final Register d = new Register("d");

        a = new Register("a");
        c = new Register("c");
        assembunny = new Assembunny(RegisterSet.of(a, b, c, d));
    }
}
