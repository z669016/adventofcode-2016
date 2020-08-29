package com.putoet.day25;

import com.putoet.utils.ResourceLines;
import com.putoet.utils.assembunny.Assembunny;
import com.putoet.utils.assembunny.Instruction;
import com.putoet.utils.assembunny.Register;
import com.putoet.utils.assembunny.RegisterSet;

import java.util.ArrayList;
import java.util.List;

public class Day25 {
    private static Register a, b, c, d;
    private static Assembunny assembunny;

    public static void main(String[] args) {
        for (int idx = 0; idx < 1000; idx++) {
            System.out.print(idx);
            System.out.print("\r");
            final List<Integer> out = runLimitedTime(50, idx);
            if (validate(out)) {
                System.out.println("\rList for value " + idx + " is " + (validate(out) ? "valid" : "invalid"));
                break;
            }
        }
    }

    private static boolean validate(List<Integer> list) {
        int bit = 0;
        for (int idx = 0; idx < list.size(); idx++) {
            if (bit != list.get(idx))
                return false;
            bit = (bit == 0) ? 1 : 0;
        }

        return true;
    }

    private static void setup() {
        a = new Register("a");
        b = new Register("b");
        c = new Register("c");
        d = new Register("d");
        assembunny = new Assembunny(RegisterSet.of(a, b, c, d));
    }

    public static List<Integer> runLimitedTime(int millis, int startValue) {
        setup();
        a.accept(startValue);
        Instruction[] instructions = assembunny.compile(ResourceLines.list("/day25.txt"));

        final List<Integer> out = new ArrayList<>();
        assembunny.setConsumer(out::add);

        final Runnable runner = new Runnable() {
            @Override
            public void run() {
                assembunny.run(instructions);
            }
        };

        try {
            final Thread thread = new Thread(runner);
            final long start = System.currentTimeMillis();
            thread.start();
            Thread.sleep(millis);
            thread.stop();

            while (thread.isAlive()) {}
        } catch (InterruptedException exc) {}

        return out;
    }
}
