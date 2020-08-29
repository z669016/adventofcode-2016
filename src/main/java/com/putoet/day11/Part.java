package com.putoet.day11;

import java.util.Objects;

public class Part {
    private final String name;

    public Part(String name) {
        assert name != null;
        name = name.trim();
        assert name.length() > 0;

        this.name = name;
    }

    public String name() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;
        Part part = (Part) o;
        return name.equals(part.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
