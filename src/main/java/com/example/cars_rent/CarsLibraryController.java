package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.control.Label;

public class CarsLibraryController {

    @FXML
    private VBox carListContainer;

    @FXML
    public void showAvailableCars() {
        loadCars(true);
    }

    @FXML
    public void showOccupiedCars() {
        loadCars(false);
    }

    @FXML
    public void showAllCars() {
        loadCars(null);
    }

    private void loadCars(Boolean onlyAvailable) {
        carListContainer.getChildren().clear();
        CarService.getCars(onlyAvailable != null && onlyAvailable).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
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
        // Логика открытия окна с деталями автомобиля
    }

    @FXML
    private Button logOutButton;

    @FXML
    private void onLogOutButtonClıck() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene secondScene = new Scene(loader.load());

        Stage stage = (Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0);
        stage.setScene(secondScene);
    }
}
