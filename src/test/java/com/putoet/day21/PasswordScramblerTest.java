package com.putoet.day21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordScramblerTest {
    private static final List<String> OPERATIONS = List.of(
            "swap position 4 with position 0",
            "swap letter d with letter b",
            "reverse positions 0 through 4",
            "rotate left 1 step",
            "move position 1 to position 4",
            "move position 3 to position 0",
            "rotate based on position of letter b",
            "rotate based on position of letter d"
    );

    @Test
    void apply() {
        final var scrambler = PasswordScrambler.of(OPERATIONS);

        final var scrambleOperations = scrambler.operations();
        assertEquals(SwapPositionOperation.class, scrambleOperations.get(0).getClass());
        assertEquals(SwapLetterOperation.class, scrambleOperations.get(1).getClass());
        assertEquals(ReverseOperation.class, scrambleOperations.get(2).getClass());
        assertEquals(RotateOperation.class, scrambleOperations.get(3).getClass());
        assertEquals(MoveOperation.class, scrambleOperations.get(4).getClass());
        assertEquals(MoveOperation.class, scrambleOperations.get(5).getClass());
        assertEquals(RotateBasedOperation.class, scrambleOperations.get(6).getClass());
        assertEquals(RotateBasedOperation.class, scrambleOperations.get(7).getClass());
        assertEquals(8, scrambleOperations.size());

        assertEquals("decab", scrambler.apply("abcde"));
        assertEquals("abcde", scrambler.unApply(scrambler.apply("abcde")));
    }

    @Test
    void of() {
        assertEquals("abcdef", PasswordScrambler.of(List.of()).apply("abcdef"));
    }
}