package com.example.cars_rent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    public static List<Car> getCars(boolean onlyAvailable) {
        List<Car> cars = new ArrayList<>();
        String query = onlyAvailable
                ? "SELECT * FROM cars WHERE status = TRUE"
                : "SELECT * FROM cars";

        try (Connection connection = SQL_Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setTransmission(resultSet.getString("transmission"));
                car.setEngineCapacity(resultSet.getDouble("engine_capacity"));
                car.setFuelType(resultSet.getString("fuel_type"));
                car.setHorsepower(resultSet.getInt("horsepower"));
                car.setDriveType(resultSet.getString("drive_type"));
                car.setAcceleration(resultSet.getDouble("acceleration"));
                car.setEngineType(resultSet.getString("engine_type"));
                car.setFuelConsumption(resultSet.getDouble("fuel_consumption"));
                car.setElectricRange(resultSet.getObject("electric_range", Integer.class));
                car.setStatus(resultSet.getBoolean("status"));
                cars.add(car);
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


    public static void reserveCar(int carId, String userEmail) throws SQLException {
        if (userEmail == null || userEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        String updateQuery = "UPDATE cars SET status = 0, reserved_by = ? WHERE id = ?";
        try (Connection connection = SQL_Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            /*statement.setInt(1, 1); // 1 = Занят */
            statement.setString(1, userEmail);
            statement.setInt(2, carId);
            statement.executeUpdate();
        }
    }


   /* public static void reserveCar(int carId, String userEmail) {
        try (Connection connection = SQL_Connect.getConnection()) {
            String query = "UPDATE cars SET status = ?, reserved_by = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "1"); // Статус "Занят"
            statement.setString(2, userEmail);
            statement.setInt(3, carId);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */
}
