package com.putoet.day17;

import com.putoet.utils.MD5;

public class Passcode {
    private final String seed;

    public Passcode(String seed) {
        assert seed != null;

        this.seed = seed;
    }

    public String forRoute(final String route) {
        assert route != null;

        return MD5.hash(seed + route).substring(0, 4).toLowerCase();
    }
}
