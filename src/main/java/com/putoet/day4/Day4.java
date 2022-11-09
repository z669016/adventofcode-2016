package com.putoet.day4;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day4 {
    public static void main(String[] args) {
        final List<String> data = ResourceLines.list("/day4.txt");
        final List<EncryptedName> encryptedNames = data.stream()
                .map(EncryptedName::from)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        final long validNames = encryptedNames.size();

        System.out.println("Number of valid rooms is: " + validNames);
        System.out.println("Sum of the secor id's is: " + encryptedNames.stream()
                .mapToInt(name -> name.sectorId())
                .sum());

        encryptedNames.forEach(encryptedName -> {
                    final String decryptedName = encryptedName.decrypt();
                    if (decryptedName.equals("northpole object storage"))
                        System.out.println("Sector id for North Pole object storage is: " + encryptedName.sectorId());
                }
        );
    }
}
