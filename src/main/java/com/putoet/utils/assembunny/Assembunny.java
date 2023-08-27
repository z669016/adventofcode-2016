package com.putoet.utils.assembunny;

import java.util.*;
import java.util.function.Consumer;

public class Assembunny {
    public static final String INTEGER_REGEXP = "^-?\\d+$";
    private static boolean verbose = false;
    private final RegisterSet regs;
    private ExecutionContext context;
    private Consumer<Integer> consumer;

    public Assembunny(RegisterSet regs) {
        assert regs != null;

        this.regs = regs;
    }

    public void setConsumer(Consumer<Integer> consumer) {
        this.consumer = consumer;
    }

    public static void enableVerbose() {
        verbose = true;
    }

    public static void disableVerbose() {
        verbose = false;
    }

    @Override
    public String toString() {
        return "Assembunny {regs:" + regs + "}";
    }

    public void run(Instruction[] program) {
        final var ip = new Register("ip");
        context = new ExecutionContext(ip, regs, program, consumer);

        while (ip.get() < program.length) {
            if (verbose)
                System.out.println(regs + " " + program[ip.get()].toString());

            ip.accept(ip.get() + program[ip.get()].execute());
        }

        context = null;
    }

    public Instruction[] compile(List<String> program) {
        final var instructions = new ArrayList<Instruction>();
        for (var idx = 0; idx < program.size(); idx++)
            instructions.add(compile(idx, program.get(idx)));

        return instructions.toArray(new Instruction[0]);
    }

    public Instruction compile(int line, String programLine) {
        if (programLine == null || programLine.isEmpty())
            return new Nop();

        final var tokens = programLine.split(" ");
        return switch(tokens[0]) {
            case "cpy" -> cpyInstruction(line, tokens);
            case "inc" -> incInstruction(line, tokens);
            case "dec" -> decInstruction(line, tokens);
            case "jnz" -> jnzInstruction(line, tokens);
            case "tgl" -> tglInstruction(line, tokens);
            case "out" -> outInstruction(line, tokens);
            default -> throw new IllegalArgumentException(compilerErrorAt(line) + "unknown instruction '" + programLine + "'");
        };
    }

    private Instruction cpyInstruction(int line, String[] tokens) {
        checkOperantCount(3, line, tokens);

        return new Cpy(inOperant(line, tokens[1]), inOperant(line, tokens[2]));
    }

    private InOperant inOperant(int line, String token) {
        return token.matches(INTEGER_REGEXP) ? new InOperant(Integer.parseInt(token.trim())) : new InOperant(checkRegister(line, token.trim()));
    }

    private Instruction incInstruction(int line, String[] tokens) {
        checkOperantCount(2, line, tokens);
        return new Inc(inOperant(line, tokens[1]));
    }

    private Instruction decInstruction(int line, String[] tokens) {
        checkOperantCount(2, line, tokens);
        return new Dec(inOperant(line, tokens[1]));
    }

    private Instruction jnzInstruction(int line, String[] tokens) {
        checkOperantCount(3, line, tokens);

        return new Jnz(inOperant(line, tokens[1]), inOperant(line, tokens[2]));
    }

    private Instruction tglInstruction(int line, String[] tokens) {
        checkOperantCount(2, line, tokens);
        return new Tgl(inOperant(line, tokens[1]), () -> context);
    }

    private Instruction outInstruction(int line, String[] tokens) {
        checkOperantCount(2, line, tokens);
        return new Out(inOperant(line, tokens[1]), () -> context);
    }

    private String compilerErrorAt(int line) {
        return "Compiler error at line " + (line + 1) + ": ";
    }

    private void checkOperantCount(int count, int line, String[] tokens) {
        if (tokens.length != count)
            throw new IllegalArgumentException(compilerErrorAt(line) + "missing operant(s)");
    }

    private Register checkRegister(int line, String name) {
        final var reg = regs.get(name);
        return reg.orElseThrow(() -> new IllegalArgumentException(compilerErrorAt(line) + "Register '" + name + "' not defined"));
    }
}
