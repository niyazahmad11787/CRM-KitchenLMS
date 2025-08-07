package com.qa.hippo.main.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties();

    public static void load(String env) {
        String path = "src/main/resources/config." + env + ".properties";
//        src/main/resources/config.staging.properties
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file: " + path);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}