package com.putoet.day11;

public abstract class AbstractPart implements Part {
    protected final String name;

    public AbstractPart(String name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public boolean isCompatible(Part otherPart) {
        return (otherPart != this && otherPart.name().equals(name));
    }

    @Override
    public String name() { return name; }
}
