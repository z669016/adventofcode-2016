package com.putoet.day19;

public class Day19 {
    public static void main(String[] args) {
        final int puzzleInput = 3004953;
        WhiteElephantParty party = new WhiteElephantParty(puzzleInput);
        Elf winner = party.play();
        System.out.println("The winner using next strategy is " + winner.id() + "  with " + winner.presents() + " presents.");
        System.out.println("Number of actives elves is " + party.activeElves());

        party = new WhiteElephantPartyOpposite(puzzleInput);
        winner = party.play();
        System.out.println("The winner using opposite strategy is " + winner.id() + "  with " + winner.presents() + " presents.");
        System.out.println("Number of actives elves is " + party.activeElves());
    }
}
