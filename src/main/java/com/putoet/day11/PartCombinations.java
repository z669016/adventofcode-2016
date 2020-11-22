package com.putoet.day11;

import java.util.*;
import java.util.stream.Collectors;

public class PartCombinations {
    private final Map<String,RTG> rtgs = new HashMap<>();
    private final Map<String,Chip> chips = new HashMap<>();

    boolean add(RTG rtg) {
        if (canAdd(rtg)) {
            rtgs.put(rtg.name(), rtg);
            return true;
        }

        return false;
    }

    boolean canAdd(RTG rtg) {
        assert rtg != null;
        assert !rtgs.containsKey(rtg.name());

        final Map<String,Chip> unconnectedChips = unconnectedChips();
        return unconnectedChips.isEmpty() || (unconnectedChips.size() == 1 && unconnectedChips.containsKey(rtg.name()));
    }

    boolean add(RTG a, RTG b) {
        if (canAdd(a, b)){
            rtgs.put(a.name(), a);
            rtgs.put(b.name(), b);
            return true;
        }

        return false;
    }

    boolean canAdd(RTG a, RTG b) {
        assert a != null && b != null;
        assert !rtgs.containsKey(a.name()) && !rtgs.containsKey(b.name());

        final Map<String,Chip> unconnectedChips = unconnectedChips();
        return unconnectedChips.isEmpty()
                || (unconnectedChips.size() == 1 && (unconnectedChips.containsKey(a.name()) || unconnectedChips.containsKey(b.name())))
                || (unconnectedChips.size() == 2 && (unconnectedChips.containsKey(a.name()) && unconnectedChips.containsKey(b.name())));
    }

    boolean add(Chip chip) {
        if (canAdd(chip)) {
            chips.put(chip.name(), chip);
            return true;
        }

        return false;
    }

    boolean canAdd(Chip chip) {
        assert chip != null;
        assert !chips.containsKey(chip.name());
        return rtgs.isEmpty() || rtg(chip.name()).isPresent();
    }

    boolean add(Chip a, Chip b) {
        if (canAdd(a, b)) {
            chips.put(a.name(), a);
            chips.put(b.name(), b);
            return true;
        }

        return false;
    }

    boolean canAdd(Chip a, Chip b) {
        assert a != null && b != null;
        assert !chips.containsKey(a.name()) && !chips.containsKey(b.name());

        return rtgs.isEmpty() || (rtg(a.name()).isPresent() && rtg(b.name()).isPresent());
    }

    boolean add(RTG rtg, Chip chip) {
        if (canAdd(rtg, chip)) {
            rtgs.put(rtg.name(), rtg);
            chips.put(chip.name(), chip);
            return true;
        }

        return false;
    }

    boolean canAdd(RTG rtg, Chip chip) {
        assert rtg != null && chip != null;
        assert !rtgs.containsKey(rtg.name()) && !chips.containsKey(chip.name());

        return rtg.name().equals(chip.name());
    }

    boolean remove(RTG rtg) {
        if (canRemove(rtg)) {
            rtgs.remove(rtg.name());
            return true;
        }
        return false;
    }

    boolean canRemove(RTG rtg) {
        assert rtg != null;
        assert rtgs.containsKey(rtg.name());

        return chips.isEmpty() || (chips.size() == 1 && chips.containsKey(rtg.name())) || !chips.containsKey(rtg.name());
    }

    boolean remove(RTG a, RTG b) {
        if (canRemove(a, b)) {
            rtgs.remove(a.name());
            rtgs.remove(b.name());
            return true;
        }

        return false;
    }

    boolean canRemove(RTG a, RTG b) {
        assert a != null && b != null;
        assert rtgs.containsKey(a.name()) && rtgs.containsKey(b.name());

        if (chips.isEmpty()
                || (chips.size() == 2 && chips.containsKey(a.name()) && chips.containsKey(b.name()))
                || (!chips.containsKey(a.name()) && !chips.containsKey(b.name()))) {
            return true;
        }

        return false;
    }

    boolean remove(Chip chip) {
        if (canRemove(chip)) {
            chips.remove(chip.name());
            return true;
        }

        return false;
    }

    boolean canRemove(Chip chip) {
        assert chip != null;
        assert chips.containsKey(chip.name());

        return true;
    }

    boolean remove(Chip a, Chip b) {
        if (canRemove(a, b)) {
            chips.remove(a.name());
            chips.remove(b.name());
            return true;
        }

        return false;
    }

    boolean canRemove(Chip a, Chip b) {
        assert a != null && b != null;
        assert chips.containsKey(a.name()) && chips.containsKey(b.name());

        return true;
    }

    boolean remove(RTG rtg, Chip chip) {
        if (canRemove(rtg, chip)) {
            rtgs.remove(rtg.name());
            chips.remove(chip.name());
            return true;
        }

        return false;
    }

    boolean canRemove(RTG rtg, Chip chip) {
        assert rtg != null && chip != null;
        assert rtgs.containsKey(rtg.name()) && chips.containsKey(chip.name());

        return rtg.name().equals(chip.name());
    }

    private Map<String,Chip> unconnectedChips() {
        return chips.values().stream()
                .filter(chip -> !rtgs.containsKey(chip.name()))
                .collect(Collectors.toMap(Chip::name, chip -> chip));
    }

    private Optional<RTG> rtg(String name) {
        return Optional.ofNullable(rtgs.get(name));
    }

    public int size() { return chips.size() + rtgs.size(); }

    public Collection<Chip> chips() { return chips.values(); }
    public Collection<RTG> rtgs() { return rtgs.values(); }

    public List<Part> parts() {
        final List<Part> parts = new ArrayList<>();
        parts.addAll(chips.values());
        parts.addAll(rtgs.values());
        
        return parts;
    }
}
