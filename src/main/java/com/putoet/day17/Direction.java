package com.putoet.day17;

enum Direction {
    UP("U"),
    RIGHT("R"),
    DOWN("D"),
    LEFT("L");

    private final String code;

    Direction(String code) {
        this.code = code;
    }

    public static Direction of(char c) {
        return switch (c) {
            case 'U'-> UP;
            case 'R'->  RIGHT;
            case 'D' -> DOWN;
            case 'L' -> LEFT;
            default -> throw new IllegalArgumentException("Invalid direction '" + c + "'");
        };
    }

    @Override
    public String toString() {
        return code;
    }
}
