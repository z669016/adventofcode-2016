package com.putoet.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class PasscodeDirection {
    private final Function<String,String> passcode;

    public PasscodeDirection(Function<String,String> passcode) {
        this.passcode = passcode;
    }

    public List<Direction> forRoute(String route) {
        final var code = passcode.apply(route);
        final var directions = new ArrayList<Direction>();
        if (isOpen(code.charAt(0))) directions.add(Direction.UP);
        if (isOpen(code.charAt(1))) directions.add(Direction.DOWN);
        if (isOpen(code.charAt(2))) directions.add(Direction.LEFT);
        if (isOpen(code.charAt(3))) directions.add(Direction.RIGHT);
        return directions;
    }

    private static boolean isOpen(char c) {
        return "bcdef".indexOf(c) != -1;
    }
}
