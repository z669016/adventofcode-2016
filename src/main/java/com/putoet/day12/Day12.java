package com.putoet.day12;

import com.putoet.utils.ResourceLines;
import com.putoet.utils.assembunny.Assembunny;
import com.putoet.utils.assembunny.Instruction;

import java.util.List;
import java.util.OptionalInt;

public class Day12 {
    public static void main(String[] args) {
        final List<String> registerNames = List.of("a", "b", "c", "d");
        Assembunny assembunny = new Assembunny(registerNames);
        Instruction[] instructions = assembunny.compile(ResourceLines.list("/day12.txt"));
        assembunny.run(instructions);

        OptionalInt a = assembunny.register("a");
        if (a.isPresent())
            System.out.println("Part 1 - Value for register 'a' is " + a.getAsInt());

        assembunny = new Assembunny(registerNames);
        assembunny.setRegister("c", 1);
        instructions = assembunny.compile(ResourceLines.list("/day12.txt"));
        assembunny.run(instructions);

        a = assembunny.register("a");
        if (a.isPresent())
            System.out.println("Part 2 - Value for register 'a' is " + a.getAsInt());



    }
}
