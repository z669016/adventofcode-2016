package com.putoet.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartCombinations {
    private final Map<String,RTG> rtgs = new HashMap<>();
    private final Map<String, Chip> chips = new HashMap<>();

    boolean remove(RTG rtg) {
        assert rtg != null;
        assert rtgs.containsKey(rtg.name());

        if (chips.isEmpty() || (chips.size() == 1 && chips.containsKey(rtg.name())) || !chips.containsKey(rtg.name()) ) {
            rtgs.remove(rtg.name());
            return true;
        }

        return false;
    }

    boolean remove(RTG a, RTG b) {
        assert a != null && b != null;
        assert rtgs.containsKey(a.name()) && rtgs.containsKey(b.name());

        if (chips.isEmpty()
                || (chips.size() == 2 && chips.containsKey(a.name()) && chips.containsKey(b.name()))
                || (!chips.containsKey(a.name()) && !chips.containsKey(b.name()))) {
            rtgs.remove(a.name());
            rtgs.remove(b.name());
            return true;
        }

        return false;
    }

    boolean remove(Chip chip) {
        assert chip != null;
        assert chips.containsKey(chip.name());

        chips.remove(chip.name());

        return true;
    }

    boolean remove(Chip a, Chip b) {
        assert a != null && b != null;
        assert chips.containsKey(a.name()) && chips.containsKey(b.name());

        chips.remove(a.name());
        chips.remove(b.name());

        return true;
    }

    boolean add(RTG rtg) {
        assert rtg != null;
        assert !rtgs.containsKey(rtg.name());

        final Map<String,Chip> unconnectedChips = unconnectedChips();
        if (unconnectedChips.isEmpty() ||
                (unconnectedChips.size() == 1 && unconnectedChips.containsKey(rtg.name()))) {
            rtgs.put(rtg.name(), rtg);
            return true;
        }

        return false;
    }

    boolean add(RTG a, RTG b) {
        assert a != null && b != null;
        assert !rtgs.containsKey(a.name()) && !rtgs.containsKey(b.name());

        final Map<String,Chip> unconnectedChips = unconnectedChips();
        if (unconnectedChips.isEmpty()
                || (unconnectedChips.size() == 1 && (unconnectedChips.containsKey(a.name()) || unconnectedChips.containsKey(b.name())))
                || (unconnectedChips.size() == 2 && (unconnectedChips.containsKey(a.name()) && unconnectedChips.containsKey(b.name())))) {
            rtgs.put(a.name(), a);
            rtgs.put(b.name(), b);
            return true;
        }

        return false;
    }

    boolean add(Chip chip) {
        assert !chips.containsKey(chip.name());

        if (rtgs.isEmpty() || rtg(chip.name()).isPresent()) {
            chips.put(chip.name(), chip);
            return true;
        }

        return false;
    }

    boolean add(Chip a, Chip b) {
        assert a != null && b != null;
        assert !chips.containsKey(a.name()) && !chips.containsKey(b.name());

        if (rtgs.isEmpty() || (rtg(a.name()).isPresent() && rtg(b.name()).isPresent())) {
            chips.put(a.name(), a);
            chips.put(b.name(), b);
            return true;
        }

        return false;
    }

    private Map<String,Chip> unconnectedChips() {
        return chips.values().stream()
                .filter(chip -> !rtgs.containsKey(chip.name()))
                .collect(Collectors.toMap(Chip::name, chip -> chip));
    }

    private Optional<RTG> rtg(String name) {
        return Optional.ofNullable(rtgs.get(name));
    }
}
