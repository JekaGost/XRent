package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class CarsLibraryController {


    @FXML
    private AnchorPane carListContainer;

    @FXML
    private void showAvailableCars() {
        openFilteredCarsWindow(true);
    }

    @FXML
    private void showOccupiedCars() {
        openFilteredCarsWindow(false);
    }

   /* @FXML
    private void showAllCars() {
        openFilteredCarsWindow(true);
    } */


    /*@FXML
    private AnchorPane carListContainer;


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
    }*/


    /*
    @FXML
    public void showAvailableCars() {
        loadAvailableCars();
    }

    @FXML
    public void showOccupiedCars() {
        loadOccupiedCars();
    }

    @FXML
    public void showAllCars() {
        loadAllCars();
    }



    private void loadAvailableCars() {
        carListContainer.getChildren().clear();
        CarService.getCars(true).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }

    private void loadOccupiedCars() {
        carListContainer.getChildren().clear();
        CarService.getCars(false).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }

    private void loadAllCars() {
        carListContainer.getChildren().clear();
        CarService.getCars(true && false).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }

    */

    private void loadCars(Boolean onlyAvailable) {
        carListContainer.getChildren().clear();

        CarService.getCars(onlyAvailable != null && onlyAvailable).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setPrefWidth(200); // Увеличение ширины кнопок
            carButton.setPrefHeight(50); // Увеличение высоты кнопок
            carButton.setStyle("-fx-font-size: 16px;"); // Увеличение размера текста
            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }

    /*private void loadCars(Boolean onlyAvailable) {
        carListContainer.getChildren().clear();

        CarService.getCars(onlyAvailable != null && onlyAvailable).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }*/

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

    private void openFilteredCarsWindow(Boolean onlyAvailable) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cars-main-page.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Получаем контроллер новой сцены и передаем фильтр
            CarsMainPageController controller = loader.getController();
            controller.loadCars(onlyAvailable);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
