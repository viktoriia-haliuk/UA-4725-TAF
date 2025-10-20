package com.softserve.academy.HW_14_Loggs.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("src/test/resources")
            .ignoreIfMissing()
            .load();

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL", "https://www.greencity.cx.ua/#/ubs");
    }

    public static String getBrowser() {
        return dotenv.get("BROWSER", "firefox");
    }

    public static String getPassword(String key) {
        String password = dotenv.get(key);

        if (password != null && password.isEmpty()) {
            return "";
        }

        if (password == null) {
            throw new IllegalArgumentException("Password for " + key + " not found in .env file");
        }

        return password;
    }

    public static String getEnvVariable(String key, String defaultValue) {
        return dotenv.get(key, defaultValue);
    }
}
