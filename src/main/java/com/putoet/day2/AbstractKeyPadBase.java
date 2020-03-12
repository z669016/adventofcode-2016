package com.putoet.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractKeyPadBase<T> implements KeyPad {
    protected final List<T> code = new ArrayList<>();

    protected int currentX;
    protected int currentY;

    @Override
    public int x() { return currentX; }

    @Override
    public int y() { return currentY; }

    public List<T> codeList() { return Collections.unmodifiableList(code); }

    @Override
    public String code() { return code.stream().map(String::valueOf).collect(Collectors.joining()); }

    @Override
    public void move(List<Direction> directions) {
        directions.forEach(this::move);
    }
}
