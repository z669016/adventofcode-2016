package com.putoet.day24;

public enum Cell {

        OPEN,
        WALL,
        GATE0,
        GATE1,
        GATE2,
        GATE3,
        GATE4,
        GATE5,
        GATE6,
        GATE7,
        GATE8,
        GATE9;

    public static Cell of(int cell) {
        return switch (cell) {
            case '.' -> OPEN;
            case '#' -> WALL;
            case '0' -> GATE0;
            case '1' -> GATE1;
            case '2' -> GATE2;
            case '3' -> GATE3;
            case '4' -> GATE4;
            case '5' -> GATE5;
            case '6' -> GATE6;
            case '7' -> GATE7;
            case '8' -> GATE8;
            case '9' -> GATE9;
            default -> throw new IllegalArgumentException("Invalid cell '" + Character.toString(cell) + "'");
        };
    }

    public boolean isGate() {
        return this != OPEN && this != WALL;
    }

    @Override
    public String toString() {
        return switch (this) {
            case OPEN -> ".";
            case WALL -> "#";
            case GATE0 -> "0";
            case GATE1 -> "1";
            case GATE2 -> "2";
            case GATE3 -> "3";
            case GATE4 -> "4";
            case GATE5 -> "5";
            case GATE6 -> "6";
            case GATE7 -> "7";
            case GATE8 -> "8";
            case GATE9 -> "9";
        };
    }
}
