package com.qa.hippo.main.utilities;

import java.util.Random;

public class RandomAddressGenerator {

    private static final String[] BUSINESS_NAMES = {
            "Tech Innovators Ltd.", "GreenField Solutions", "Pinnacle Software",
            "NextGen Enterprises", "Elite Builders", "Nova Tech", "SmartCom Inc.",
            "BlueWave Technologies", "Vertex Dynamics", "Prime Architects"
    };

    private static final String[] STREET_NAMES = {
            "Main Street", "Park Avenue", "Sunset Boulevard", "Elm Street",
            "Highland Road", "Cedar Lane", "Maple Drive", "Oak Boulevard"
    };

    private static final String[] ZIP_CODES = {
            "110007", "110003", "110014", "110024", "122002",
            "110065", "122003", "122017", "122101", "122104"
    };

    public static String generateBusinessName() {
        Random random = new Random();
        return BUSINESS_NAMES[random.nextInt(BUSINESS_NAMES.length)];
    }

    public static String generateBusinessAddressLine() {
        Random random = new Random();
        int houseNumber = random.nextInt(9999) + 1; // Random house number
        String streetName = STREET_NAMES[random.nextInt(STREET_NAMES.length)];
        return houseNumber + " " + streetName;
    }

    public static String generateZipCode() {
        Random random = new Random();
        return ZIP_CODES[random.nextInt(ZIP_CODES.length)];
    }

    public static void main(String[] args) {

        String add=generateBusinessAddressLine();
        System.out.println(add);

    }
}
