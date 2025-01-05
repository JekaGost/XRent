package com.example.cars_rent;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;

import java.util.Optional;

public class PopupUtils {
    public static String showInputDialog(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(message);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public static void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hata");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
