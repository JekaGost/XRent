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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Integer getElectricRange() {
        return electricRange;
    }

    public void setElectricRange(Integer electricRange) {
        this.electricRange = electricRange;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
