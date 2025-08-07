package com.qa.hippo.main.utilities;

import java.util.Random;

public class RandomNameGenerator {

    private static final String[] FIRST_NAMES = {
            "Alice", "Bob", "Charlie", "David", "Emma",
            "Fiona", "George", "Hannah", "Ian", "Julia","Tom"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Martinez", "Lopez","Johnson"
    };

    public static String generateRandomFirstName() {
        Random random = new Random();
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    public static String generateRandomLastName() {
        Random random = new Random();
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

}

