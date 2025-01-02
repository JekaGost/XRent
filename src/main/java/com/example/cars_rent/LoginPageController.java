package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;



public class LoginPageController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private UserService userService = new UserService();

    @FXML
    private void onLoginClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();


        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Поля не должны быть пустыми.");
            return;
        }

        boolean isLoginSuccessful = userService.loginUser(username, password);

        if (isLoginSuccessful) {
            showAlert(Alert.AlertType.INFORMATION, "Успех", "Вы успешно вошли в систему.");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    UserSession.isAdmin() ? "cars-library-page.fxml" : "cars-main-page.fxml"
            ));

            Scene nextScene = new Scene(loader.load(),880, 900);
            Stage stage = (Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0);
            stage.setScene(nextScene);

        } else {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Неверное имя пользователя или пароль.");
        }
    }

            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("cars-main-page.fxml"));
            Scene secondScene = new Scene(loader.load());

            Stage stage = (Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0);
            stage.setScene(secondScene);
        }*/

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
        }
    }
