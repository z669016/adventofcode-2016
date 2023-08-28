package com.putoet.day25;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;
import com.putoet.utils.assembunny.Assembunny;
import com.putoet.utils.assembunny.Instruction;
import com.putoet.utils.assembunny.Register;
import com.putoet.utils.assembunny.RegisterSet;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Day25 {
    private static Register a;
    private static Assembunny assembunny;

    public static void main(String[] args) {
        Timer.run(() -> {
            for (var idx = 0; idx < 1000; idx++) {
                System.out.print(idx);
                System.out.print("\r");
                final var out = runLimitedTime(50, idx);
                if (validate(out)) {
                    System.out.println("\rList for value " + idx + " is " + (validate(out) ? "valid" : "invalid"));
                    break;
                }
            }
        });
    }

    private static boolean validate(List<Integer> list) {
        var bit = 0;
        for (var integer : list) {
            if (bit != integer)
                return false;
            bit = (bit == 0) ? 1 : 0;
        }

        return true;
    }

    private static void setup() {
        a = new Register("a");
        final var b = new Register("b");
        final var c = new Register("c");
        final var d = new Register("d");
        assembunny = new Assembunny(RegisterSet.of(a, b, c, d));
    }

    @SneakyThrows
    public static List<Integer> runLimitedTime(int millis, int startValue) {
        setup();
        a.accept(startValue);
        Instruction[] instructions = assembunny.compile(ResourceLines.list("/day25.txt"));

        final var out = new ArrayList<Integer>();
        assembunny.setConsumer(out::add);

        final Runnable runner = () -> assembunny.run(instructions);
        final var thread = new Thread(runner);
        thread.start();
        Thread.sleep(millis);
        thread.interrupt();

        return out;
    }
}
