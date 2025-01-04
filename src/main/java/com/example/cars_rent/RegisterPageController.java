package com.example.cars_rent;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;



public class RegisterPageController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Button register_anasayfa_button;

    private UserService userService = new UserService();

    @FXML
    private void onRegisterDevamButton() {

        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Hata", "Bütün alanlar doldurulmalıdır.");
            return;
        }

        boolean isRegistered = userService.registerUser(username, email, password, firstName, lastName, phoneNumber);

        if (isRegistered) {
            showAlert(Alert.AlertType.INFORMATION, "Başarı", "Başarılı bir şekilde kayıt oldunuz.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Hata", "Kayıt hatası. Bu E-posta veya Kullanıcı Adı daha önce sistemde kaydedilmiştir.");
        }
    }

    @FXML
    private void onRegisterAnasayfaButton() {
        Stage stage = (Stage) register_anasayfa_button.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
