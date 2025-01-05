package com.example.cars_rent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CarsLibrary {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarsLibrary.class.getResource("cars-library-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 880, 900);
        stage.setTitle("Admin Paneli");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/XRent_Icon.png")));
        stage.setScene(scene);
        stage.show();
    }
}
