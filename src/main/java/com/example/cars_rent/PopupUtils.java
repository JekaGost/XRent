package com.example.cars_rent;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PopupUtils {
    public static String showInputDialog(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(message);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
