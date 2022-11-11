package com.putoet.day4;

import com.putoet.resources.ResourceLines;

import java.util.Optional;

public class Day4 {
    public static void main(String[] args) {
        final var encryptedNames = ResourceLines.stream("/day4.txt")
                .map(EncryptedName::from)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        System.out.println("Number of valid rooms is: " + encryptedNames.size());
        System.out.println("Sum of the sector id's is: " + encryptedNames.stream()
                .mapToInt(EncryptedName::sectorId)
                .sum());

        System.out.println("Sector id for North Pole object storage is: " + encryptedNames.stream()
                .filter(name -> name.decrypt().contains("northpole"))
                .findFirst()
                .orElseThrow()
                .sectorId()
        );
    }
}
