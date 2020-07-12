package com.putoet.day17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasscodeDirectionTest {
    @Test
    void forRoute() {
        assertEquals(List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT), new PasscodeDirection(code -> "bbbb").forRoute(""));
        assertEquals(List.of(Direction.UP, Direction.DOWN, Direction.LEFT), new PasscodeDirection(code -> "bbb1").forRoute(""));
        assertEquals(List.of(Direction.UP, Direction.DOWN), new PasscodeDirection(code -> "bb11").forRoute(""));
        assertEquals(List.of(Direction.UP), new PasscodeDirection(code -> "b111").forRoute(""));
        assertEquals(List.of(), new PasscodeDirection(code -> "1111").forRoute(""));
    }
}