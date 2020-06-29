package com.putoet.utils.assembunny;

import java.util.*;
import java.util.stream.Collectors;

public class Assembunny {
    public static final String INTEGER_REGEXP = "^-?\\d+$";
    private static boolean verbose = false;
    private Map<String, Register> regs = new HashMap<>();

    public Assembunny(List<String> registerNames) {
        registerNames.forEach(name -> regs.put(name, new Register(name)));
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
        int ip = 0;
        while (ip < program.length) {
            if (verbose)
                System.out.println(regsToString() + " " + program[ip].toString());

            ip += program[ip].execute();
        }
    }

    private String regsToString() {
        return "[" +
                regs.values().stream().map(register -> register.name() + ":" + register.get()).collect(Collectors.joining(", ")) +
                "]";
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
        switch(tokens[0]) {
            case "cpy": return cpyInstruction(line, tokens);
            case "inc": return incInstruction(line, tokens);
            case "dec": return decInstruction(line, tokens);
            case "jnz": return jnzInstruction(line, tokens);
        }
        throw new IllegalArgumentException(compilerErrorAt(line) + "unknown instruction '" + programLine + "'");
    }

    private Instruction cpyInstruction(int line, String[] tokens) {
        checkOperantCount(3, line, tokens);

        final boolean constant = tokens[1].matches("^-?\\d+$");
        if (!constant) checkRegister(line, tokens[1].trim());
        checkRegister(line, tokens[2].trim());

        if (constant)
            return new Cpy(new InOperant(Integer.parseInt(tokens[1].trim())), regs.get(tokens[2].trim()));
        else
            return new Cpy(new InOperant(regs.get(tokens[1].trim())), regs.get(tokens[2].trim()));
    }

    private Instruction incInstruction(int line, String[] tokens) {
        checkOperantCount(2, line, tokens);
        checkRegister(line, tokens[1].trim());

        return new Inc(regs.get(tokens[1].trim()));
    }

    private Instruction decInstruction(int line, String[] tokens) {
        checkOperantCount(2, line, tokens);
        checkRegister(line, tokens[1].trim());

        return new Dec(regs.get(tokens[1].trim()));
    }

    private Instruction jnzInstruction(int line, String[] tokens) {
        checkOperantCount(3, line, tokens);
        if (!tokens[2].matches(INTEGER_REGEXP))
            throw new IllegalArgumentException(compilerErrorAt(line) + "Invalid offset '" + tokens[2] + "'");

        final boolean constant = tokens[1].matches(INTEGER_REGEXP);
        if (!constant) checkRegister(line, tokens[1].trim());

        if (constant)
            return new Jnz(new InOperant(Integer.parseInt(tokens[1].trim())), new InOperant(Integer.parseInt(tokens[2].trim())));
        else
            return new Jnz(new InOperant(regs.get(tokens[1].trim())), new InOperant(Integer.parseInt(tokens[2].trim())));
    }

    private String compilerErrorAt(int line) {
        return "Compiler error at line " + (line + 1) + ": ";
    }

    private void checkOperantCount(int count, int line, String[] tokens) {
        if (tokens.length != count)
            throw new IllegalArgumentException(compilerErrorAt(line) + "missing operant(s)");
    }

    private void checkRegister(int line, String name) {
        if (!regs.containsKey(name))
            throw new IllegalArgumentException(compilerErrorAt(line) + "Register '" + name + "' not defined");
    }

    public OptionalInt register(String name) {
        return regs.containsKey(name) ? OptionalInt.of(regs.get(name).get()) : OptionalInt.empty();
    }

    public void setRegister(String name, int value) {
        if (regs.containsKey(name))
            regs.get(name).accept(value);
    }
}
