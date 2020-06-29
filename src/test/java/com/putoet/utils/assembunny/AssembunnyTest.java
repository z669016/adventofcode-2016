package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssembunnyTest {

    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Assembunny(List.of("")));
        assertThrows(AssertionError.class, () -> new Assembunny(List.of("1")));
    }

    @Test
    void run() {
        final Instruction nop = mock(Nop.class);
        when(nop.execute()).thenReturn(1);

        final Assembunny assembunny = new Assembunny(List.of("a", "b"));
        final Instruction[] instructions = new Instruction[]{nop, nop, nop};

        Assembunny.enableVerbose();
        assembunny.run(instructions);
        verify(nop, times(instructions.length)).execute();
    }

    @Test
    void register() {
        final Assembunny assembunny = new Assembunny(List.of("a", "b"));
        assertTrue(assembunny.register("a").isPresent());
        assertTrue(assembunny.register("b").isPresent());
        assertFalse(assembunny.register("c").isPresent());
    }

    @Test
    void compile() {
        final List<String> program = List.of(
                "cpy 3 a",
                "cpy a b",
                "inc b",
                "dec a",
                "jnz a 4"
        );
        final Assembunny assembunny = new Assembunny(List.of("a", "b"));
        Instruction[] instructions = assembunny.compile(program);

        for (int idx = 0; idx < program.size(); idx++)
            assertEquals(program.get(idx), instructions[idx].toString());
    }

    @Test
    void compilerError() {
        final Assembunny assembunny = new Assembunny(List.of("a", "b"));

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
        final List<String> program = List.of(
                "cpy 41 a",
                "inc a",
                "inc a",
                "dec a",
                "jnz a 2",
                "dec a"
        );
        final Assembunny assembunny = new Assembunny(List.of("a", "b"));
        final Instruction[] instructions = assembunny.compile(program);
        assembunny.run(instructions);
        assertTrue(assembunny.register("a").isPresent());
        assertEquals(42, assembunny.register("a").getAsInt());
    }
}