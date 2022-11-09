package com.putoet.day17;

import com.putoet.security.MD5;
import lombok.SneakyThrows;

public class Passcode {
    private final String seed;

    public Passcode(String seed) {
        assert seed != null;

        this.seed = seed;
    }

    @SneakyThrows
    public String forRoute(final String route) {
        assert route != null;

        return MD5.hash(seed + route).substring(0, 4).toLowerCase();
    }
}
