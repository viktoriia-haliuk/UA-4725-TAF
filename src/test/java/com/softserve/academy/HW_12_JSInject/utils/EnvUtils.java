package com.softserve.academy.HW_12_JSInject.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("src/test/resources")
            .ignoreIfMissing()
            .load();

    public static String getPassword(String key) {
        String password = dotenv.get(key);
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password for " + key + " not found in .env file");
        }
        return password;
    }
}
