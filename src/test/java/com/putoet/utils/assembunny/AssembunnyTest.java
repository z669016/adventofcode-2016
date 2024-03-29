package com.putoet.utils.assembunny;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AssembunnyTest {
    private Register a;
    private Assembunny assembunny;

    @BeforeEach
    void setup() {
        a = new Register("a");
        Register b = new Register("b");
        RegisterSet regs = RegisterSet.of(a, b);
        assembunny = new Assembunny(regs);
    }

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Assembunny(null));
    }

    @Test
    void run() {
        final var nop = mock(Nop.class);
        when(nop.execute()).thenReturn(1);

        final Instruction[] instructions = new Instruction[]{nop, nop, nop};

        Assembunny.enableVerbose();
        assembunny.run(instructions);
        Assembunny.disableVerbose();
        verify(nop, times(instructions.length)).execute();
    }

    @Test
    void compile() {
        final var program = List.of(
                "cpy 3 a",
                "cpy a b",
                "inc b",
                "dec a",
                "jnz a 4"
        );
        final var instructions = assembunny.compile(program);

        for (var idx = 0; idx < program.size(); idx++)
            assertEquals(program.get(idx), instructions[idx].toString());
    }

    @Test
    void compilerError() {
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("cpy")));
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("cpy a")));
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("cpy 1 c")));
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("cpy c a")));

        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("inc")));
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("inc c")));

        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("dec")));
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("dec c")));

        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("jnz")));
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("jnz c 1")));
        assertThrows(IllegalArgumentException.class, () -> assembunny.compile(List.of("jnz c a")));
    }

    @Test
    void program() {
        final var program = List.of(
                "cpy 41 a",
                "inc a",
                "inc a",
                "dec a",
                "jnz a 2",
                "dec a"
        );
        final var instructions = assembunny.compile(program);
        assembunny.run(instructions);
        assertEquals(42, a.get());
    }

    @Test
    void toggle() {
        final var program = List.of(
                "cpy 2 a",
                "tgl a",
                "tgl a",
                "tgl a",
                "cpy 1 a",
                "dec a",
                "dec a"
        );
        final var instructions = assembunny.compile(program);
        assembunny.run(instructions);
        assertEquals(3, a.get());
    }
}