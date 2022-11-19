package com.putoet.day11b;

public enum DeviceType {
    RTG("G"),
    MICROCHIP("M");

    private final String code;
    DeviceType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
