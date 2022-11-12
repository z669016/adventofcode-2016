package com.putoet.day8;

import com.putoet.utils.FixedGrid;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardReader implements Supplier<Optional<Consumer<FixedGrid<Integer>>>> {
    private static final Pattern RECT_PATTERN = Pattern.compile("rect (\\d+)x(\\d+)");
    private static final Pattern ROTATE_COLUMN_PATTERN = Pattern.compile("rotate column x=(\\d+) by (\\d+)");
    private static final Pattern ROTATE_ROW_PATTERN = Pattern.compile("rotate row y=(\\d+) by (\\d+)");

    private final List<String> instructions;
    private int idx = 0;

    public CardReader(List<String> instructions) {
        this.instructions = instructions;
    }

    @Override
    public Optional<Consumer<FixedGrid<Integer>>> get() {
        if (idx < instructions.size())
            return Optional.of(decode(instructions.get(idx++)));

        return Optional.empty();
    }

    private Consumer<FixedGrid<Integer>> decode(String instruction) {
        instruction = instruction.trim().toLowerCase();
        if (instruction.contains("rect")) {
            final Matcher matcher = RECT_PATTERN.matcher(instruction);
            if (matcher.matches())
                return new RectInstruction(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } else if (instruction.contains("column")) {
            final Matcher matcher = ROTATE_COLUMN_PATTERN.matcher(instruction);
            if (matcher.matches())
                return new RotateColumnInstruction(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } else if (instruction.contains("row")) {
            final Matcher matcher = ROTATE_ROW_PATTERN.matcher(instruction);
            if (matcher.matches())
                return new RotateRowInstruction(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }

        throw new IllegalArgumentException("Invalid instruction '" + instruction + "'");
    }
}
