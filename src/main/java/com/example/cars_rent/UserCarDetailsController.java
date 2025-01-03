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

import java.util.Random;

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
        try {
            // 1. Сгенерировать случайный код
            generatedCode = generateVerificationCode();

            // 2. Отправить код на email пользователя
            EmailService.sendVerificationCode(userEmail, generatedCode);

            // 3. Показать всплывающее окно для ввода кода
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Подтверждение резервирования");
            dialog.setHeaderText("Введите код, отправленный на ваш email.");
            dialog.setContentText("Код подтверждения:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String enteredCode = result.get();

                // 4. Проверить, совпадает ли введенный код с отправленным
                if (generatedCode.equals(enteredCode)) {
                    // Код верный, резервируем автомобиль
                    CarService.reserveCar(carId, userEmail); // Метод для обновления в базе
                    showAlert2("Успех", "Автомобиль успешно зарезервирован!", Alert.AlertType.INFORMATION);
                } else {
                    // Код неверный
                    showAlert2("Ошибка", "Неверный код подтверждения.", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert2("Ошибка", "Произошла ошибка при резервировании автомобиля.", Alert.AlertType.ERROR);
        }
    }

    // Генерация случайного 6-значного кода
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Генерация числа от 100000 до 999999
        return String.valueOf(code);
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

    private void showAlert2(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        // Логика возврата к предыдущему экрану
    }
}
