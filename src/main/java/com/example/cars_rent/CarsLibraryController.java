package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.control.Label;

public class CarsLibraryController {

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
