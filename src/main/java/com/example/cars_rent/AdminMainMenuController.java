package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMainMenuController {

    public void onAdminMenuButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cars-library-page.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Admin Paneli");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/XRent_Icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onProfilAdminMenuButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Profil");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/XRent_Icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogOutButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene secondScene = new Scene(loader.load(), 920, 900);

        Stage stage = (Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0);
        stage.setScene(secondScene);
    }

}
