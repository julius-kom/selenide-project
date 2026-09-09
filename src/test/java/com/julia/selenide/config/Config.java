package com.julia.selenide.config;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class Config {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = Config.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            PROPERTIES.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("base.url");
    }

}