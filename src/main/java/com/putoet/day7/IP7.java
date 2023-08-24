package com.putoet.day7;

import java.util.ArrayList;
import java.util.List;

record IP7(String address) {

    public boolean supportsTLS() {
        final var hyperNetSequences = hyperNetSequences(address);
        final var superNetSequences = superNetSequences(address);

        return hyperNetSequences.stream().noneMatch(ABBA::containsABBA)
                && superNetSequences.stream().anyMatch(ABBA::containsABBA);
    }

    public boolean supportSSL() {
        final var hyperNetSequences = hyperNetSequences(address);
        final var superNetSequences = superNetSequences(address);

        final var abaList = new ArrayList<String>();
        superNetSequences.forEach(section -> abaList.addAll(ABBA.listABA(section)));

        return hyperNetSequences.stream().anyMatch(section -> ABBA.containsBAB(abaList, section));
    }

    static List<String> hyperNetSequences(String address) {
        final var betweenBrackets = new ArrayList<String>();

        var copy = address;
        while (copy.contains("[")) {
            if (copy.indexOf("]") < copy.indexOf("["))
                throw new IllegalArgumentException("Invalid address IP7 address: " + address);

            final var text = copy.substring(copy.indexOf("[") + 1, copy.indexOf("]"));
            if (!text.isEmpty())
                betweenBrackets.add(text);

            copy = copy.substring(copy.indexOf("]") + 1);
        }

        return betweenBrackets;
    }

    static List<String> superNetSequences(String address) {
        final var outsideBrackets = new ArrayList<String>();

        var copy = address;
        while (copy.contains("[")) {
            if (copy.indexOf("]") < copy.indexOf("["))
                throw new IllegalArgumentException("Invalid address IP7 address: " + address);

            final var text = copy.substring(0, copy.indexOf("["));
            if (!text.isEmpty())
                outsideBrackets.add(text);

            copy = copy.substring(copy.indexOf("]") + 1);
        }
        if (!copy.isEmpty())
            outsideBrackets.add(copy);

        return outsideBrackets;
    }
}
