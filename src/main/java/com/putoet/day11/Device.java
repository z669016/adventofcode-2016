package com.putoet.day11;

public abstract class Device {
    private final String name;

    public Device(String name) {
        assert name != null && name.length() > 0;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String code() {
        return toString().toUpperCase();
    }
}
