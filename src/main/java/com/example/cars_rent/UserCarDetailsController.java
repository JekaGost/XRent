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
    private String generatedCode;
    /*private String userEmail = "user@example.com"; // Замените на текущий email пользователя*/

    @FXML
    public void onReserveButtonClick() {
        try {
            if (currentCar == null) {
                showAlert("Hata", "Araç seçilmemiştir.", Alert.AlertType.ERROR);
                return;
            }

            // Генерация кода подтверждения
            generatedCode = generateVerificationCode();

            // Отправка кода на email пользователя
            EmailService.sendVerificationCode(UserSession.getEmail(), generatedCode);

            // Диалоговое окно для ввода кода
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Rezervasyon onayı");
            dialog.setHeaderText("E-postanıza gönderilen kodu giriniz.");
            dialog.setContentText("Onay Kodu:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String enteredCode = result.get();

                if (generatedCode.equals(enteredCode)) {
                    // Резервируем автомобиль
                    CarService.reserveCar(currentCar.getId(), UserSession.getEmail());
                    showAlert("Başarı", "Araç başarıyla rezerve edildi!", Alert.AlertType.INFORMATION);

                    // Обновление статуса
                    currentCar.setStatus(false);
                    updateCarStatus();
                } else {
                    showAlert("Hata", "Geçersiz onay kodu.", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hata", "Araç rezervasyonu yaparken bir hata oluştu.", Alert.AlertType.ERROR);
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void updateCarStatus() {
        statusLabel.setText("Durum: " + (currentCar.isStatus() ? "Serbest" : "Dolu"));
        statusIndicator.setFill(currentCar.isStatus() ? Color.GREEN : Color.RED);
        reserveButton.setText(currentCar.isStatus() ? "Rezerve Yap" : "İptal Et");
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
    }
}
