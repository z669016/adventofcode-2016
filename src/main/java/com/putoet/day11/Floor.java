package com.putoet.day11;

import java.util.ArrayList;
import java.util.List;

record Floor(int elevator, List<FloorState> items) {
    public Floor {
        assert items != null;
        assert elevator >= 0 && elevator < items.size();
    }

    public boolean isInvalid() {
        for (var item : items) {
            int chips = item.microchips();
            int rtgs = item.generators();

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
                if (current.microchips() > i) {
                    next.add(createMoveUp(new FloorState(current.microchips() - i - 1, current.generators()),
                            new FloorState(up.microchips() + i + 1, up.generators()))
                    );
                }
            }

            // Move 1 or 2 rtgs
            for (int i = 0; i < 2; i++) {
                if (current.generators() > i) {
                    next.add(createMoveUp(new FloorState(current.microchips(), current.generators() - i - 1),
                            new FloorState(up.microchips(), up.generators() + i + 1))
                    );
                }
            }

            // Move a pair
            if (current.microchips() > 0 && current.generators() > 0) {
                next.add(createMoveUp(new FloorState(current.microchips() - 1, current.generators() - 1),
                       new FloorState(up.microchips() + 1, up.generators() + 1))
                );
            }
        }

        if (canMoveDown()) {
            final var current = items.get(elevator);
            final var down = items.get(elevator - 1);

            // Move 1 or 2 chips
            for (int i = 0; i < 2; i++) {
                if (current.microchips() > i) {
                    next.add(createMoveDown(new FloorState(current.microchips() - i - 1, current.generators()),
                            new FloorState(down.microchips() + i + 1, down.generators()))
                    );
                }
            }

            // Move 1 or 2 rtgs
            for (int i = 0; i < 2; i++) {
                if (current.generators() > i) {
                    next.add(createMoveDown(new FloorState(current.microchips(), current.generators() - i - 1),
                            new FloorState(down.microchips(), down.generators() + i + 1))
                    );
                }
            }

            // Move a pair
            if (current.microchips() > 0 && current.generators() > 0) {
                next.add(createMoveDown(new FloorState(current.microchips() - 1, current.generators() - 1),
                        new FloorState(down.microchips() + 1, down.generators() + 1))
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

    public Floor createMoveUp(FloorState current, FloorState up) {
        final List<FloorState> next = new ArrayList<>();

        for (int i = 0; i < elevator; i++)
            next.add(items.get(i));

        next.add(current);
        next.add(up);

        for (int i = elevator + 2; i < items.size(); i++)
            next.add(items.get(i));

        return new Floor(elevator + 1, next);
    }

    public Floor createMoveDown(FloorState current, FloorState down) {
        final List<FloorState> next = new ArrayList<>();

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
            final int chips = item.microchips();
            final int rtgs = item.generators();
            if (chips != 0 || rtgs != 0)
                return false;
        }

        return true;
    }
}
