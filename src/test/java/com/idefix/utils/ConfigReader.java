package com.idefix.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties file could not be found.");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException exception) {
            throw new IllegalStateException("config.properties file could not be read.", exception);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key, "").trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
