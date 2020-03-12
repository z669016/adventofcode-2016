package com.putoet.utils;

public interface FixedGrid<T> {
    T get(int x, int y);

    int height();

    int width();
}
