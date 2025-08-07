package com.qa.hippo.main.utilities;

import java.io.*;
import java.util.Properties;

public class ConfigWriter {

    // ✅ Set your path here correctly
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.staging.properties";

    public static void setProperty(String key, String value) {
        try {
            Properties props = new Properties();

            // Load existing properties
            InputStream input = new FileInputStream(CONFIG_FILE_PATH);
            props.load(input);
            input.close();

            // Set or overwrite the property
            props.setProperty(key, value);

            // Save back to file
            OutputStream output = new FileOutputStream(CONFIG_FILE_PATH);
            props.store(output, "Updated by automation");
            output.close();

            System.out.println("✅ Property saved to config.staging.properties: " + key + " = " + value);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to update config.staging.properties: " + e.getMessage());
        }
    }
}


