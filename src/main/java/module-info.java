module com.example.cars_rent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.example.cars_rent to javafx.fxml;
    exports com.example.cars_rent;
}