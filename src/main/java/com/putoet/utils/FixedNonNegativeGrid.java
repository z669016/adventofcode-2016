package com.putoet.utils;

import java.util.ArrayList;
import java.util.List;

public class FixedNonNegativeGrid<T> implements FixedGrid<T> {
    private final List<List<T>> grid;
    private final T defaultValue;
    private final int height;
    private final int width;
    private final boolean hasDefaultValue;

    public FixedNonNegativeGrid(int height, int width, T defaultValue) {
        assert height > 0;
        assert width > 0;
        assert defaultValue != null;

        this.height = height;
        this.width = width;
        this.defaultValue = defaultValue;
        this.hasDefaultValue = true;
        this.grid = new ArrayList<>();

        for (int idy = 0; idy < height; idy++) {
            final List<T> row = new ArrayList<>();
            for (int idx = 0; idx < width; idx++)
                row.add(idx, defaultValue);

            grid.add(idy,row);
        }
    }

    public FixedNonNegativeGrid(List<List<T>> matrix) {
        assert matrix != null;
        for (List<T> ts : matrix) assert ts != null;

        this.height = matrix.size();
        this.width = matrix.get(0).size();
        this.defaultValue = null;
        this.hasDefaultValue = false;

        for (List<T> ts : matrix) assert ts.size() == this.width;

        this.grid = new ArrayList<>(matrix);
    }

    @Override
    public T get(int x, int y) {
        assertCoordinates(x, y);
        return grid.get(y).get(x);
    }

    @Override
    public int height() { return height; }
    @Override
    public int width() { return width; }

    private void assertCoordinates(int x, int y) {
        if (y >= height)
            throw new IllegalArgumentException("Invalid Y coordinate (height is " + height + ")");
        if (x >= width)
            throw new IllegalArgumentException("Invalid X coordinate (width is " + width + ")");
    }
}
