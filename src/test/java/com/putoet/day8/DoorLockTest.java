package com.putoet.day8;

import com.putoet.utils.FixedGrid;
import com.putoet.utils.FixedNonNegativeGrid;
import org.junit.jupiter.api.Test;

import java.util.List;

class DoorLockTest {

    @Test
    void display() {
        final FixedGrid<Integer> grid = new FixedNonNegativeGrid<>(List.of(
                List.of(1, 1, 1, 0, 0, 0, 0),
                List.of(1, 1, 1, 0, 0, 0, 0),
                List.of(0, 0, 0, 0, 0, 0, 0)
        ));

        DoorLock.display(grid);
    }
}