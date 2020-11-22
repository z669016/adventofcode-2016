package com.putoet.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PossibleMoves {
    public static List<Set<? extends Part>> possibleMoves(PartCombinations from, PartCombinations to) {
        assert from != null && to != null;

        if (from.size() == 0) {
            return List.of();
        }

        final List<Set<? extends Part>> possibleMoves = new ArrayList<>();

        // add all possible moves with individual chips
        possibleMoves.addAll(from.chips().stream()
                .filter(chip -> from.canRemove(chip) && to.canAdd(chip))
                .map(Set::of)
                .collect(Collectors.toList()));

        // add all possible moves with individual RTGs
        possibleMoves.addAll(from.rtgs().stream()
                .filter(rtg -> from.canRemove(rtg) && to.canAdd(rtg))
                .map(Set::of)
                .collect(Collectors.toList()));


        return possibleMoves;
    }
}
