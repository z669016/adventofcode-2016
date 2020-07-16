package com.putoet.day21;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordScrambler implements ScrambleOperation {
    private final List<ScrambleOperation> operations;

    public PasswordScrambler(List<ScrambleOperation> operations) {
        assert operations != null;

        this.operations = operations;
    }

    @Override
    public String apply(String password) {
        assert password != null;

        for (ScrambleOperation operation : operations) {
            password = operation.apply(password);
        }
        return password;
    }

    public List<ScrambleOperation> operations() {
        return Collections.unmodifiableList(operations);
    }

    public static PasswordScrambler of(List<String> lines) {
        assert lines != null;

        return new PasswordScrambler(lines.stream()
                .sequential()
                .map(ScrambleOperation::of)
                .collect(Collectors.toList())
        );
    }
}