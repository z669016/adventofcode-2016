package com.putoet.utils;

public interface FixedGrid<T> {
    T get(int x, int y);
    void set(int x, int y, T value);

    int height();

    int width();
}
