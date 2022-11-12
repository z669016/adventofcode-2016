package com.putoet.day7;

import java.util.ArrayList;
import java.util.List;

public record IP7(String address) {

    public boolean supportsTLS() {
        final List<String> hyperNetSequences = hyperNetSequences(address);
        final List<String> superNetSequences = superNetSequences(address);

        return hyperNetSequences.stream().noneMatch(ABBA::containsABBA)
                && superNetSequences.stream().anyMatch(ABBA::containsABBA);
    }

    public boolean supportSSL() {
        final List<String> hyperNetSequences = hyperNetSequences(address);
        final List<String> superNetSequences = superNetSequences(address);

        final List<String> abaList = new ArrayList<>();
        superNetSequences.forEach(section -> abaList.addAll(ABBA.listABA(section)));

        return hyperNetSequences.stream().anyMatch(section -> ABBA.containsBAB(abaList, section));
    }

    static List<String> hyperNetSequences(String address) {
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

    static List<String> superNetSequences(String address) {
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
}
