package com.jfisherdev.ezopenjmx.util;

/**
 * @author Josh Fisher
 */
public final class EZOpenJMXStringUtils {

    private EZOpenJMXStringUtils() {
    }

    public static String checkNotNullOrEmpty(String string) {
        return checkNotNullOrEmpty(string, "String may not be null or empty.");
    }

    public static String checkNotNullOrEmpty(String string, String message) {
        if (isNullOrEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
        return string;
    }

    public static boolean isNullOrEmpty(String string) {
        return trimToEmpty(string).isEmpty();
    }

    public static String trimToEmpty(String string) {
        return string != null ? string.trim() : "";
    }


}
