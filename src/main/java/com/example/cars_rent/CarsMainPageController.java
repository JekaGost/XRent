package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CarsMainPageController {

    public class CarsMainPage {
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(RegisterPage.class.getResource("cars-main-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 880, 900);
            stage.setTitle("Araçlar listesi");
            stage.setScene(scene);
            stage.show();
        }
    }

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
            carButton.setPrefWidth(200);  // Увеличиваем размер кнопок
            carButton.setPrefHeight(50);  // Увеличиваем размер кнопок
            carButton.setStyle("-fx-font-size: 14px; -fx-background-color: #f0f0f0;");

            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }

    private void openCarDetails(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("car-details.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

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
