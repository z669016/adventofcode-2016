package com.putoet.day21;

public interface ScrambleOperation {

    static ScrambleOperation of(String line) {
        final String[] split = line.split(" ");
        return switch (split[0]) {
            case "move" -> new MoveOperation(split);
            case "reverse" -> new ReverseOperation(split);
            case "swap" -> "position".equals(split[1]) ? new SwapPositionOperation(split) : new SwapLetterOperation(split);
            case "rotate" -> "based".equals(split[1]) ? new RotateBasedOperation(split) : new RotateOperation(split);
            default -> throw new IllegalArgumentException("Invalid operation '" + line + "'");
        };
    }

    String apply(String password);
    String unApply(String password);
}
