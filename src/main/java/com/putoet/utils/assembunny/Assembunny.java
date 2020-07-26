package com.putoet.utils.assembunny;

import java.util.*;

public class Assembunny {
    public static final String INTEGER_REGEXP = "^-?\\d+$";
    private static boolean verbose = false;
    private final RegisterSet regs;
    private ExecutionContext context;

    public Assembunny(RegisterSet regs) {
        assert regs != null;

        this.regs = regs;
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
        final Register ip = new Register("ip");
        context = new ExecutionContext(ip, regs, program);

        while (ip.get() < program.length) {
            if (verbose)
                System.out.println(regs.toString() + " " + program[ip.get()].toString());

            ip.accept(ip.get() + program[ip.get()].execute());
        }

        context = null;
    }

    public Instruction[] compile(List<String> program) {
        final List<Instruction> instructions = new ArrayList<>();
        for (int idx = 0; idx < program.size(); idx++)
            instructions.add(compile(idx, program.get(idx)));

        return instructions.toArray(new Instruction[0]);
    }

    public Instruction compile(int line, String programLine) {
        if (programLine == null || programLine.length() == 0)
            return new Nop();

        final String[] tokens = programLine.split(" ");
        return switch(tokens[0]) {
            case "cpy" -> cpyInstruction(line, tokens);
            case "inc" -> incInstruction(line, tokens);
            case "dec" -> decInstruction(line, tokens);
            case "jnz" -> jnzInstruction(line, tokens);
            case "tgl" -> tglInstruction(line, tokens);
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

    private String compilerErrorAt(int line) {
        return "Compiler error at line " + (line + 1) + ": ";
    }

    private void checkOperantCount(int count, int line, String[] tokens) {
        if (tokens.length != count)
            throw new IllegalArgumentException(compilerErrorAt(line) + "missing operant(s)");
    }

    private Register checkRegister(int line, String name) {
        final Optional<Register> reg = regs.get(name);
        if (reg.isEmpty())
            throw new IllegalArgumentException(compilerErrorAt(line) + "Register '" + name + "' not defined");

        return reg.get();
    }

    public RegisterSet regs() {
        return regs;
    }
}
