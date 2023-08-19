package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.Optional;

public class Day4 {
    public static void main(String[] args) {
        final var encryptedNames = Timer.run(() -> {
            final var names = ResourceLines.stream("/day4.txt")
                    .map(EncryptedName::of)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();


            System.out.println("Number of valid rooms is: " + names.size());
            System.out.println("Sum of the sector id's is: " + names.stream()
                    .mapToInt(EncryptedName::sectorId)
                    .sum());

            return names;
        });

        Timer.run(() ->
                System.out.println("Sector id for North Pole object storage is: " + encryptedNames.stream()
                        .filter(name -> name.decrypt().contains("northpole"))
                        .findFirst()
                        .orElseThrow()
                        .sectorId())
        );
    }
}
