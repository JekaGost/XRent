package com.example.cars_rent;

public class CarsMainController {

    /* @FXML
    private VBox carListContainer;

    /*private void loadCars(Boolean onlyAvailable) {
        carListContainer.getChildren().clear();
        List<Car> cars = CarService.getCars(onlyAvailable != null && onlyAvailable);

        int columns = 6;
        int row = 0;
        int column = 0;

        for (Car car : cars) {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setPrefWidth(120);
            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.add(carButton, column++, row);

            if (column == columns) {
                column = 0;
                row++;
            }
        }
    } */

    /*private void loadCars(Boolean onlyAvailable) {
        carListContainer.getChildren().clear();
        CarService.getCars(onlyAvailable != null && onlyAvailable).forEach(car -> {
            Button carButton = new Button(car.getBrand() + " " + car.getModel());
            carButton.setOnAction(event -> openCarDetails(car));
            carListContainer.getChildren().add(carButton);
        });
    }

    @FXML
    public void initialize() {
        loadAvailableCars();
    }


    private void loadAvailableCars() {
        carListContainer.getChildren().clear();
        CarService.getCars(true).forEach(car -> {
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
    private void onExitButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene secondScene = new Scene(loader.load());

        Stage stage = (Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0);
        stage.setScene(secondScene);
    } */
}
