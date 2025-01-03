package com.example.cars_rent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterPage.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 880, 900);
        stage.setTitle("Register Page");
        stage.setScene(scene);
        stage.show();
    }
}

/*private void MainMenu() {

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        CarsMainController controller = loader.getController();

        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    // Логика открытия окна с деталями автомобиля
}*/

