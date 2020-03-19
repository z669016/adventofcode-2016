package com.putoet.day9;

import java.util.regex.Pattern;

public interface Sequence {
    Pattern REPEAT_SEQUENCE = Pattern.compile("^(\\((\\d+)x(\\d+)\\))(.*)");

    long length();
    String text();
}
