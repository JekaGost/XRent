package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ProfileController {
    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        loadUserData();
    }

    private int userId; // ID текущего пользователя

    public void setUserId(int userId) {
        this.userId = userId;
        loadUserData();
    }

    private void loadUserData() {
        try {
            User user = UserService.getUserById(userId); // Получаем данные из БД
            firstNameLabel.setText(user.getFirstName());
            lastNameLabel.setText(user.getLastName());
            emailLabel.setText(user.getEmail());
            phoneNumberLabel.setText(user.getPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editFirstName() {
        String newFirstName = PopupUtils.showInputDialog("Введите новое имя:");
        if (newFirstName != null) {
            UserService.updateUserField(userId, "first_name", newFirstName);
            loadUserData();
        }
    }

    @FXML
    private void editLastName() {
        String newLastName = PopupUtils.showInputDialog("Введите новую фамилию:");
        if (newLastName != null) {
            UserService.updateUserField(userId, "last_name", newLastName);
            loadUserData();
        }
    }

    @FXML
    private void editEmail() {
        String newEmail = PopupUtils.showInputDialog("Введите новый Email:");
        if (newEmail != null) {
            UserService.updateUserField(userId, "email", newEmail);
            loadUserData();
        }
    }

    @FXML
    private void editPhoneNumber() {
        String newPhoneNumber = PopupUtils.showInputDialog("Введите новый номер телефона:");
        if (newPhoneNumber != null) {
            UserService.updateUserField(userId, "phone_number", newPhoneNumber);
            loadUserData();
        }
    }

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) firstNameLabel.getScene().getWindow();
        stage.close(); // Закрываем текущее окно
    }
}
