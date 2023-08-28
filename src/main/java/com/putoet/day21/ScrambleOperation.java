package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

interface ScrambleOperation {

    static ScrambleOperation of(@NotNull String line) {
        final var split = line.split(" ");
        return switch (split[0]) {
            case "move" -> new MoveOperation(split);
            case "reverse" -> new ReverseOperation(split);
            case "swap" -> "position".equals(split[1]) ? new SwapPositionOperation(split) : new SwapLetterOperation(split);
            case "rotate" -> "based".equals(split[1]) ? new RotateBasedOperation(split) : new RotateOperation(split);
            default -> throw new IllegalArgumentException("Invalid operation '" + line + "'");
        };
    }

    String apply(@NotNull String password);
    String unApply(@NotNull String password);
}
