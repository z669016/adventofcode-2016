package com.putoet.day11b;

public record Generator(String name, DeviceType type) implements Device {
    public Generator {
        assert name != null && name.length() > 0;
        assert type != null;
    }

    public Generator(String name) {
        this(name, DeviceType.RTG);
    }

    @Override
    public String code() {
        return Character.toUpperCase(name.charAt(0)) + type.code();
    }
}
