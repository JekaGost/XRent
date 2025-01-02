package com.example.cars_rent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CarLibraryDetailsController {

    public class CarDetailsController {
        @FXML
        private Label carTitleLabel;
        @FXML
        private Label transmissionLabel;
        @FXML
        private Label engineCapacityLabel;
        @FXML
        private Label fuelTypeLabel;
        @FXML
        private Label horsepowerLabel;
        @FXML
        private Label driveTypeLabel;
        @FXML
        private Label accelerationLabel;
        @FXML
        private Label engineTypeLabel;
        @FXML
        private Label fuelConsumptionLabel;
        @FXML
        private Label electricRangeLabel;
        @FXML
        private Label statusLabel;
        @FXML
        private Rectangle statusIndicator;
        @FXML
        private Button reserveButton;
        @FXML
        private Button backButton;


        private Car currentCar;

        public void setCarDetails(Car car) {
            this.currentCar = car;
            carTitleLabel.setText(car.getBrand() + " " + car.getModel());
            transmissionLabel.setText("Коробка передач: " + car.getTransmission());
            engineCapacityLabel.setText("Объем двигателя: " + car.getEngineCapacity() + " л");
            fuelTypeLabel.setText("Тип топлива: " + car.getFuelType());
            horsepowerLabel.setText("Мощность: " + car.getHorsepower() + " л.с.");
            driveTypeLabel.setText("Тип привода: " + car.getDriveType());
            accelerationLabel.setText("Разгон до 100 км/ч: " + car.getAcceleration() + " с");
            engineTypeLabel.setText("Тип двигателя: " + car.getEngineType());
            fuelConsumptionLabel.setText("Расход топлива: " + car.getFuelConsumption() + " л/100 км");

            if (car.getElectricRange() != null) {
                electricRangeLabel.setText("Запас хода на электричестве: " + car.getElectricRange() + " км");
                electricRangeLabel.setVisible(true);
            }

            statusLabel.setText("Статус: " + (car.isStatus() ? "Свободен" : "Занят"));
            statusIndicator.setFill(car.isStatus() ? Color.GREEN : Color.RED);

            reserveButton.setText(car.isStatus() ? "Зарезервировать" : "Освободить");
        }

        @FXML
        private void onReserveButtonClick() {
            boolean newStatus = !currentCar.isStatus();
            if (CarService.updateCarStatus(currentCar.getId(), newStatus)) {
                currentCar.setStatus(newStatus);
                setCarDetails(currentCar);
            } else {
                // Вывести ошибку
            }
        }

        @FXML
        private void onBackButtonClick() {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            // Логика возврата к предыдущему экрану
        }
    }

}
