package com.example.cars_rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public boolean registerUser(String username, String email, String password, String firstName, String lastName, String phoneNumber) {
        String sql = "INSERT INTO users (username, email, password, first_name, last_name, phone_number, isAdmin) VALUES (?, ?, MD5(?), ?, ?, ?, ?)";

        try (Connection connection = SQL_Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.setString(6, phoneNumber);
            statement.setBoolean(7, false); // Новые пользователи не администраторы

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean loginUser(String username, String password) {
        String sql = "SELECT isAdmin FROM users WHERE username = ? AND password = MD5(?)";

        try (Connection connection = SQL_Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                boolean isAdmin = resultSet.getBoolean("isAdmin");
                UserSession.setIsAdmin(isAdmin); // Сохраняем статус в сессии
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static User getUserById(int userId) throws Exception {
        Connection connection = SQL_Connect.getConnection();
        String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("phone_number"),
                    resultSet.getBoolean("isAdmin"),
                    resultSet.getString("password")
            );
        }
        throw new Exception("User not found");
    }

    public static void updateUserField(int userId, String fieldName, String newValue) {
        try {
            Connection connection = SQL_Connect.getConnection();
            String query = "UPDATE users SET " + fieldName + " = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newValue);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
