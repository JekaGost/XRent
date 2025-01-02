package com.example.cars_rent;

public class Car {
    private int id;
    private String brand;
    private String model;
    private String transmission;
    private double engineCapacity;
    private String fuelType;
    private int horsepower;
    private String driveType;
    private double acceleration;
    private String engineType;
    private double fuelConsumption;
    private Integer electricRange;
    private boolean status;

    public Car(int id, String brand, String model, String transmission, double engineCapacity, String fuelType, int horsepower,
               String driveType, double acceleration, String engineType, double fuelConsumption, Integer electricRange, boolean status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.transmission = transmission;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.horsepower = horsepower;
        this.driveType = driveType;
        this.acceleration = acceleration;
        this.engineType = engineType;
        this.fuelConsumption = fuelConsumption;
        this.electricRange = electricRange;
        this.status = status;
    }
}
