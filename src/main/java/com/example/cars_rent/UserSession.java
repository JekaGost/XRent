package com.example.cars_rent;

public class UserSession {
    private static int userId;
    private static boolean isAdmin;
    private static String email;

    public static void setEmail(String email) {
        UserSession.email = email;
    }

    public static String getEmail() {
        return UserSession.email;
    }

    public static void setUserId(int userId) {
        UserSession.userId = userId;
    }

    public static int getUserId() {
        return UserSession.userId;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        UserSession.isAdmin = isAdmin;
    }
}
