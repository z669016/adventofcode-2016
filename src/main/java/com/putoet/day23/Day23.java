package com.putoet.day23;

import com.putoet.utils.ResourceLines;
import com.putoet.utils.assembunny.Assembunny;
import com.putoet.utils.assembunny.Instruction;
import com.putoet.utils.assembunny.Register;
import com.putoet.utils.assembunny.RegisterSet;

public class Day23 {
    private static Register a, b, c, d;
    private static Assembunny assembunny;

    public static void main(String[] args) {
        setup();
        a.accept(7);
        Instruction[] instructions = assembunny.compile(ResourceLines.list("/day23.txt"));
        assembunny.run(instructions);
        System.out.println("Part 1 - Value for register 'a' is " + a.get());

        setup();
        a.accept(12);
        instructions = assembunny.compile(ResourceLines.list("/day23.txt"));
        assembunny.run(instructions);
        System.out.println("Part 2 - Value for register 'a' is " + a.get());
    }

    private static void setup() {
        a = new Register("a");
        b = new Register("b");
        c = new Register("c");
        d = new Register("d");
        assembunny = new Assembunny(RegisterSet.of(a, b, c, d));
    }
}
