package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class CarDetailsController {
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
    @FXML
    private Button clearReservationButton;


    private Car currentCar;
    private String generatedCode;

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

        /*reserveButton.setText(car.isStatus() ? "Зарезервировать" : "Освободить");*/
    }

    @FXML
    private void onClearReservationButtonCllick() {
        boolean newStatus = true;
        if (CarService.updateCarStatus(currentCar.getId(), newStatus)) {
            currentCar.setStatus(newStatus);
            setCarDetails(currentCar);
        } else {
            // Вывести ошибку
        }
    }

    @FXML
    public void onReserveButtonClick() {
        try {
            if (currentCar == null) {
                showAlert("Ошибка", "Автомобиль не выбран.", Alert.AlertType.ERROR);
                return;
            }

            // Генерация кода подтверждения
            generatedCode = generateVerificationCode();

            // Отправка кода на email пользователя
            EmailService.sendVerificationCode(UserSession.getEmail(), generatedCode);

            // Диалоговое окно для ввода кода
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Подтверждение резервирования");
            dialog.setHeaderText("Введите код, отправленный на ваш email.");
            dialog.setContentText("Код подтверждения:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String enteredCode = result.get();

                if (generatedCode.equals(enteredCode)) {
                    // Резервируем автомобиль
                    CarService.reserveCar(currentCar.getId(), UserSession.getEmail());
                    showAlert("Успех", "Автомобиль успешно зарезервирован!", Alert.AlertType.INFORMATION);

                    // Обновление статуса
                    currentCar.setStatus(false);
                    updateCarStatus();
                } else {
                    showAlert("Ошибка", "Неверный код подтверждения.", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ошибка", "Произошла ошибка при резервировании автомобиля.", Alert.AlertType.ERROR);
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void updateCarStatus() {
        statusLabel.setText("Статус: " + (currentCar.isStatus() ? "Свободен" : "Занят"));
        statusIndicator.setFill(currentCar.isStatus() ? Color.GREEN : Color.RED);
        reserveButton.setText(currentCar.isStatus() ? "Зарезервировать" : "Освободить");
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
