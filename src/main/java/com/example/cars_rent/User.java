package com.example.cars_rent;

public class User {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isAdmin;
    private String password;

    // Конструктор
    public User(int id, String username, String email, String firstName, String lastName, String phoneNumber, boolean isAdmin, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }

    // Сеттеры (если необходимы)
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
