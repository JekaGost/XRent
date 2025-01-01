package com.example.cars_rent;

public class UserSession {
    private static boolean isAdmin;

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        UserSession.isAdmin = isAdmin;
    }
}
