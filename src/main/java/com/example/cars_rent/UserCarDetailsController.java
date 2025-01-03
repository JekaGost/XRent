package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class UserCarDetailsController {

    @FXML
    private Label carTitleLabel;
    @FXML
    private Label transmissionLabel;
    @FXML
    private Label engineCapacityLabel;
    @FXML
    private Label fuelTypeLabel;
    @FXML
    private Label horsepowerLabel;
    @FXML
    private Label driveTypeLabel;
    @FXML
    private Label accelerationLabel;
    @FXML
    private Label engineTypeLabel;
    @FXML
    private Label fuelConsumptionLabel;
    @FXML
    private Label electricRangeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Rectangle statusIndicator;
    @FXML
    private Button reserveButton;
    @FXML
    private Button backButton;

    private Car currentCar;

    public void setCarDetails(Car car) {
        this.currentCar = car;
        carTitleLabel.setText(car.getBrand() + " " + car.getModel());
        transmissionLabel.setText("Коробка передач: " + car.getTransmission());
        engineCapacityLabel.setText("Объем двигателя: " + car.getEngineCapacity() + " л");
        fuelTypeLabel.setText("Тип топлива: " + car.getFuelType());
        horsepowerLabel.setText("Мощность: " + car.getHorsepower() + " л.с.");
        driveTypeLabel.setText("Тип привода: " + car.getDriveType());
        accelerationLabel.setText("Разгон до 100 км/ч: " + car.getAcceleration() + " с");
        engineTypeLabel.setText("Тип двигателя: " + car.getEngineType());
        fuelConsumptionLabel.setText("Расход топлива: " + car.getFuelConsumption() + " л/100 км");

        if (car.getElectricRange() != null) {
            electricRangeLabel.setText("Запас хода на электричестве: " + car.getElectricRange() + " км");
            electricRangeLabel.setVisible(true);
        }

        statusLabel.setText("Статус: " + (car.isStatus() ? "Свободен" : "Занят"));
        statusIndicator.setFill(car.isStatus() ? Color.GREEN : Color.RED);

        reserveButton.setText(car.isStatus() ? "Зарезервировать" : "Освободить");
    }

    public void onReserveButtonClick() {
        // Отправка кода подтверждения на email
        String code = EmailService.sendVerificationCodeToUser("user_email@example.com");

        // Всплывающее окно для ввода кода
        String enteredCode = showInputDialog("Введите код подтверждения:");
        if (enteredCode != null && enteredCode.equals(code)) {
            // Резервируем автомобиль
            CarService.reserveCar(carId);
            updateCarStatus();
            showAlert("Успех", "Автомобиль успешно зарезервирован!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Ошибка", "Неверный код подтверждения.", Alert.AlertType.ERROR);
        }
    }

    private void updateCarStatus() {
        // Логика обновления статуса автомобиля
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        // Логика возврата к предыдущему экрану
    }
}
