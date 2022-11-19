package com.putoet.day11b;

public record Microchip(String name, DeviceType type) implements Device {
    public Microchip {
        assert name != null && name.length() > 0;
        assert type != null;
    }

    public Microchip(String name) {
        this(name, DeviceType.MICROCHIP);
    }

    @Override
    public String code() {
        return Character.toUpperCase(name.charAt(0)) + type.code();
    }
}
