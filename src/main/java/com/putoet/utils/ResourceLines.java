package com.putoet.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceLines {
    public static Stream<String> stream(String resourceName) {
        try {
            final URL url = ResourceLines.class.getResource(resourceName);
            final Path path = Paths.get(url.toURI());
            return Files.lines(path);
        } catch (URISyntaxException | IOException exc) {
            throw new IllegalArgumentException("Invalid resource name '" + resourceName + "'", exc);
        }
    }

    public static List<String> list(String resourceName) {
        return stream(resourceName).collect(Collectors.toList());
    }

    public static List<String> csv(String resourceName) {
        return stream(resourceName)
                .map(line -> line.split(","))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
