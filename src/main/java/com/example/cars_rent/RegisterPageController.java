package com.example.cars_rent;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;



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

    private UserService userService = new UserService();

    @FXML
    private Label register_label;

    @FXML
    private void onRegisterDevamButton() throws IOException {

        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Все поля должны быть заполнены.");
            return;
        }

        boolean isRegistered = userService.registerUser(username, email, password, firstName, lastName, phoneNumber);

        if (isRegistered) {
            showAlert(Alert.AlertType.INFORMATION, "Успех", "Вы успешно зарегистрировались.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Ошибка при регистрации.");
        }


        // Меняем текст заголовка
        /*register_label.setText("Kayıt Tamamlandı!");

        // Таймер для возврата на главную страницу через 8 секунд
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> { // Переход на главную страницу
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                        Scene mainScene = new Scene(loader.load());
                        Stage stage = (Stage) register_label.getScene().getWindow();
                        stage.setScene(mainScene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }, 4000); // 8000 миллисекунд = 8 секунд */
    }

    @FXML
    private void onRegisterAnasayfaButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene secondScene = new Scene(loader.load());

        Stage stage = (Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0);
        stage.setScene(secondScene);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
