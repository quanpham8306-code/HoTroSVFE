package org.example.Util;

public class UrlUtil {

    public static String build(String endpoint) {
        return endpoint;
    }

    public static String build(String endpoint, Object value) {
        return endpoint.replaceFirst("\\{[^/]+}", value.toString());
    }

    public static String build(String endpoint, Object... values) {
        String result = endpoint;

        for (Object value : values) {
            result = result.replaceFirst("\\{[^/]+}", value.toString());
        }

        return result;
    }
}