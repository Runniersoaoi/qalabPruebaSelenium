package com.ntt.store.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final String FILE_NAME = "config.properties";
    private static final Properties PROPERTIES = load();

    private ConfigReader() { }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(FILE_NAME)) {

            if (input == null) {
                throw new IllegalStateException(
                        FILE_NAME + " was not found in src/test/resources");
            }
            properties.load(input);
            return properties;

        } catch (IOException e) {
            throw new IllegalStateException("Could not read " + FILE_NAME, e);
        }
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Property '" + key + "' is not defined in " + FILE_NAME);
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static String appUrl() {
        return get("app.url");
    }

    public static int explicitTimeout() {
        return getInt("timeout.explicit");
    }
}