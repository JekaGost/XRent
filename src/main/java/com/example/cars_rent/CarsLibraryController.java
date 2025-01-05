package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class CarsLibraryController {



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
            stage.setTitle("Araçların Listesi");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/XRent_Icon.png")));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
