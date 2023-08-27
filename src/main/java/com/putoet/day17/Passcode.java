package com.putoet.day17;

import com.putoet.security.MD5;
import lombok.SneakyThrows;

record Passcode(String seed) {
    public Passcode {
        assert seed != null;
    }

    @SneakyThrows
    public String forRoute(final String route) {
        assert route != null;

        return MD5.hash(seed + route).substring(0, 4).toLowerCase();
    }
}
