package com.putoet.day7;

import java.util.ArrayList;
import java.util.List;

public class IP7 {
    private final String address;

    public IP7(String address) {
        this.address = address;
    }

    public boolean supportSSL() {
        final List<String> hypernetSequences = hypernetSequences(address);
        final List<String> supernetSequences = supernetSequences(address);

        final List<String> abaList = new ArrayList<>();
        supernetSequences.forEach(section -> abaList.addAll(ABBA.listABA(section)));

        return hypernetSequences.stream().anyMatch(section -> ABBA.containsBAB(abaList, section));
    }

    public boolean supportsTLS() {
        final List<String> hypernetSequences = hypernetSequences(address);
        final List<String> supernetSequences = supernetSequences(address);

        return hypernetSequences.stream().noneMatch(ABBA::containsABBA)
                && supernetSequences.stream().anyMatch(ABBA::containsABBA);
    }

    static List<String> supernetSequences(String address) {
        final List<String> outsideBrackets = new ArrayList<>();

        String copy = address;
        while (copy.contains("[")) {
            if (copy.indexOf("]") < copy.indexOf("["))
                throw new IllegalArgumentException("Invalid address IP7 address: " + address);

            final String text = copy.substring(0, copy.indexOf("["));
            if (!text.isEmpty())
                outsideBrackets.add(text);

            copy = copy.substring(copy.indexOf("]") + 1);
        }
        if (!copy.isEmpty())
            outsideBrackets.add(copy);

        return outsideBrackets;
    }

    static List<String> hypernetSequences(String address) {
        final List<String> betweenBrackets = new ArrayList<>();

        String copy = address;
        while (copy.contains("[")) {
            if (copy.indexOf("]") < copy.indexOf("["))
                throw new IllegalArgumentException("Invalid address IP7 address: " + address);

            final String text = copy.substring(copy.indexOf("[") + 1, copy.indexOf("]"));
            if (!text.isEmpty())
                betweenBrackets.add(text);

            copy = copy.substring(copy.indexOf("]") + 1);
        }

        return betweenBrackets;
    }
}
