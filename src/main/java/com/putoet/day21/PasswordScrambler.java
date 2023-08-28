package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

record PasswordScrambler(@NotNull List<ScrambleOperation> operations) implements ScrambleOperation {
    @Override
    public String apply(@NotNull String password) {
        for (var operation : operations) {
            password = operation.apply(password);
        }
        return password;
    }

    @Override
    public String unApply(@NotNull String password) {
        for (var idx = operations.size() - 1; idx >= 0; idx--) {
            password = operations.get(idx).unApply(password);
        }
        return password;
    }

    @Override
    public List<ScrambleOperation> operations() {
        return Collections.unmodifiableList(operations);
    }

    public static PasswordScrambler of(List<String> lines) {
        assert lines != null;

        return new PasswordScrambler(lines.stream()
                .map(ScrambleOperation::of)
                .toList()
        );
    }
}