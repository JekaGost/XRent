package com.example.cars_rent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    public static List<Car> getCars(boolean onlyAvailable) {
        List<Car> cars = new ArrayList<>();
        String query = onlyAvailable ? "SELECT * FROM cars WHERE status = TRUE" : "SELECT * FROM cars";

        try (Connection connection = SQL_Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                cars.add(new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("transmission"),
                        resultSet.getDouble("engine_capacity"),
                        resultSet.getString("fuel_type"),
                        resultSet.getInt("horsepower"),
                        resultSet.getString("drive_type"),
                        resultSet.getDouble("acceleration"),
                        resultSet.getString("engine_type"),
                        resultSet.getDouble("fuel_consumption"),
                        resultSet.getObject("electric_range", Integer.class),
                        resultSet.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public static boolean updateCarStatus(int carId, boolean status) {
        String query = "UPDATE cars SET status = ? WHERE id = ?";
        try (Connection connection = SQL_Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, status);
            statement.setInt(2, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
