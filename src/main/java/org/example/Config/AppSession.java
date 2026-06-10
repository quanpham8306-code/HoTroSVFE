package org.example.Config;

public class AppSession {

    private static String token;
    private static String username;
    private static String role;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        AppSession.token = token;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        AppSession.username = username;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        AppSession.role = role;
    }

    public static void clear() {
        token = null;
        username = null;
        role = null;
    }
}
