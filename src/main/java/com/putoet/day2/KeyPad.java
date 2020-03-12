package com.putoet.day2;

import java.util.List;

public interface KeyPad {
    enum Direction {
        U, D, L, R;
    }

    int x();

    int y();

    void move(List<SquareKeyPad.Direction> directions);

    void move(SquareKeyPad.Direction direction);

    void press();

    String code();
}
