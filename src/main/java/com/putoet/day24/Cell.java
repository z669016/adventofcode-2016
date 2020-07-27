package com.putoet.day24;

public class Cell {
    public enum Type {
        OPEN, WALL, GATE;
    }

    private final Type type;
    private final int number;

    private Cell(Type type) {
        this.type = type;
        this.number = -1;
    }

    private Cell(int number) {
        this.type = Type.GATE;
        this.number = number;
    }

    public Type type() { return type; }
    public int number() {
        if (type != Type.GATE)
            throw new IllegalStateException("Cannot get the number for a cell of type " + type);

        return number;
    }

    public static Cell of(int cell) {
        if (cell >= '0' && cell <= '9')
            return new Cell(cell - '0');

        return switch (cell) {
            case '.' -> new Cell(Type.OPEN);
            case '#' -> new Cell(Type.WALL);
            default -> throw new IllegalArgumentException("Invalid cell '" + Character.toString(cell) + "'");
        };
    }

    @Override
    public String toString() {
        return switch (type) {
            case OPEN -> ".";
            case WALL -> "#";
            case GATE -> String.valueOf(number);
        };
    }
}
