module com.example.cars_rent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cars_rent to javafx.fxml;
    exports com.example.cars_rent;

}