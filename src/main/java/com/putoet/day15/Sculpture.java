package com.putoet.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Sculpture {
    private final List<Disk> disks;
    private List<Function<Integer,Integer>> positions;

    public Sculpture(List<Disk> disks) {
        assert disks != null;
        assert disks.size() > 0;

        this.disks = new ArrayList<>(disks);
        this.positions = asPositions(disks);
    }

    private static List<Function<Integer, Integer>> asPositions(List<Disk> disks) {
        return disks.stream().map(Disk::position).toList();
    }

    public void add(Disk disk) {
        assert disk != null;

        disks.add(disk);
        positions = asPositions(disks);
    }

    public boolean isOpenOn(int t){
        return positions.stream().map(f -> f.apply(t)).mapToInt(Integer::intValue).sum() == 0;
    }
}
