package com.putoet.day11;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public record Floor(int elevator, List<Pair<Integer, Integer>> items) {
    public Floor {
        assert items != null;
        assert elevator >= 0 && elevator < items.size();
    }

    public boolean isInvalid() {
        for (Pair<Integer, Integer> item : items) {
            int chips = item.getValue0();
            int rtgs = item.getValue1();

            if (chips != 0 && rtgs != 0 && chips > rtgs)
                return true;
        }
        return false;
    }

    public List<Floor> next() {
        final List<Floor> next = new ArrayList<>();
        if (canMoveUp()) {
            final var current = items.get(elevator);
            final var up = items.get(elevator + 1);

            // Move 1 or 2 chips
            for (int i = 0; i < 2; i++) {
                if (current.getValue0() > i) {
                    next.add(createMoveUp(Pair.with(current.getValue0() - i - 1, current.getValue1()),
                            Pair.with(up.getValue0() + i + 1, up.getValue1()))
                    );
                }
            }

            // Move 1 or 2 rtgs
            for (int i = 0; i < 2; i++) {
                if (current.getValue1() > i) {
                    next.add(createMoveUp(Pair.with(current.getValue0(), current.getValue1() - i - 1),
                            Pair.with(up.getValue0(), up.getValue1() + i + 1))
                    );
                }
            }

            // Move a pair
            if (current.getValue0() > 0 && current.getValue1() > 0) {
                next.add(createMoveUp(Pair.with(current.getValue0() - 1, current.getValue1() - 1),
                        Pair.with(up.getValue0() + 1, up.getValue1() + 1))
                );
            }
        }

        if (canMoveDown()) {
            final var current = items.get(elevator);
            final var down = items.get(elevator - 1);

            // Move 1 or 2 chips
            for (int i = 0; i < 2; i++) {
                if (current.getValue0() > i) {
                    next.add(createMoveDown(Pair.with(current.getValue0() - i - 1, current.getValue1()),
                            Pair.with(down.getValue0() + i + 1, down.getValue1()))
                    );
                }
            }

            // Move 1 or 2 rtgs
            for (int i = 0; i < 2; i++) {
                if (current.getValue1() > i) {
                    next.add(createMoveDown(Pair.with(current.getValue0(), current.getValue1() - i - 1),
                            Pair.with(down.getValue0(), down.getValue1() + i + 1))
                    );
                }
            }

            // Move a pair
            if (current.getValue0() > 0 && current.getValue1() > 0) {
                next.add(createMoveDown(Pair.with(current.getValue0() - 1, current.getValue1() - 1),
                        Pair.with(down.getValue0() + 1, down.getValue1() + 1))
                );
            }
        }

        return next.stream().filter(floor -> !floor.isInvalid()).toList();
    }

    public boolean done() {
        return elevator == items.size() - 1 && belowIsEmpty();
    }

    public boolean canMoveUp() {
        return elevator < items().size() - 1;
    }

    public Floor createMoveUp(Pair<Integer, Integer> current, Pair<Integer, Integer> up) {
        final List<Pair<Integer, Integer>> next = new ArrayList<>();

        for (int i = 0; i < elevator; i++)
            next.add(items.get(i));

        next.add(current);
        next.add(up);

        for (int i = elevator + 2; i < items.size(); i++)
            next.add(items.get(i));

        return new Floor(elevator + 1, next);
    }

    public Floor createMoveDown(Pair<Integer, Integer> current, Pair<Integer, Integer> down) {
        final List<Pair<Integer, Integer>> next = new ArrayList<>();

        for (int i = 0; i < elevator - 1; i++)
            next.add(items.get(i));

        next.add(down);
        next.add(current);

        for (int i = elevator + 1; i < items.size(); i++)
            next.add(items.get(i));

        return new Floor(elevator - 1, next);
    }

    public boolean canMoveDown() {
        return elevator > 0 && !belowIsEmpty();
    }

    public boolean belowIsEmpty() {
        for (int i = 0; i < elevator; i++) {
            final var item = items.get(i);
            final int chips = item.getValue0();
            final int rtgs = item.getValue1();
            if (chips != 0 || rtgs != 0)
                return false;
        }

        return true;
    }
}
