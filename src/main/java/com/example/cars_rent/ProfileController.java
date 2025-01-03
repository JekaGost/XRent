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

    //private int userId; // ID текущего пользователя
/*
    public void setUserId(int userId) {
        this.userId = userId;
        loadUserData();
    }
    */

    private void setFirstNameLabel(String firstName) {
        this.firstNameLabel.setText(firstName);
    }


    private void loadUserData() {
        try {
            User user = UserService.getUserById(); // Получаем данные из БД
            //this.setUserId(user.getId());
            this.setFirstNameLabel(user.getFirstName());
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
        String newFirstName = PopupUtils.showInputDialog("Yeni isim giriniz:");
        if (newFirstName != null) {
            UserService.updateUserField(UserSession.getUserId(), "first_name", newFirstName);
            loadUserData();
        }
    }

    @FXML
    private void editLastName() {
        String newLastName = PopupUtils.showInputDialog("Yeni soyadı giriniz:");
        if (newLastName != null) {
            UserService.updateUserField(UserSession.getUserId(), "last_name", newLastName);
            loadUserData();
        }
    }

    @FXML
    private void editEmail() {
        String newEmail = PopupUtils.showInputDialog("Yeni E-postayı giriniz:");
        if (newEmail != null) {
            UserService.updateUserField(UserSession.getUserId(), "email", newEmail);
            loadUserData();
        }
    }

    @FXML
    private void editPhoneNumber() {
        String newPhoneNumber = PopupUtils.showInputDialog("Yeni telefon numarasını giriniz:");
        if (newPhoneNumber != null) {
            UserService.updateUserField(UserSession.getUserId(), "phone_number", newPhoneNumber);
            loadUserData();
        }
    }

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) firstNameLabel.getScene().getWindow();
        stage.close(); // Закрываем текущее окно
    }
}
