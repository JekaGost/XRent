package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CarsMainPageController {

    @FXML
    private VBox carListContainer;

    @FXML
    public void initialize() {
        loadCars(true);
    }

    public void loadCars(Boolean onlyAvailable) {
        carListContainer.getChildren().clear();

        CarService.getCars(onlyAvailable).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setPrefWidth(600);  // Увеличиваем размер кнопок
            carButton.setPrefHeight(50);  // Увеличиваем размер кнопок
            carButton.setStyle("-fx-font-size: 14px; -fx-background-color: #f0f0f0;");

            carButton.setOnAction(_ -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }

    private void openCarDetails(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("car-details.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Aracın Detayları");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/XRent_Icon.png")));


            CarDetailsController controller = loader.getController();
            controller.setCarDetails(car);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) carListContainer.getScene().getWindow();
        stage.close();  // Закрываем текущее окно
    }
}
