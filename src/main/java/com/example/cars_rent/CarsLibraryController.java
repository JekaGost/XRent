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
