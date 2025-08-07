package com.qa.hippo.main.utilities;

import java.util.Random;

public class MobileNumberGenerator {

    public static String generateMobileNumber() {
        Random random = new Random();

        // Generate the first digit as 6, 7, 8, or 9
        int firstDigit = 6 + random.nextInt(4);

        // Generate the remaining 9 digits randomly (0-9)
        StringBuilder mobileNumber = new StringBuilder();
        mobileNumber.append(firstDigit);

        for (int i = 0; i < 9; i++) {
            mobileNumber.append(random.nextInt(10));
        }

        return mobileNumber.toString();
    }

}

