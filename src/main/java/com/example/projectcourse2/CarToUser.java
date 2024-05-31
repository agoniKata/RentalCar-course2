package com.example.projectcourse2;

public class CarToUser {
    public String name;
    public String brand;

    public CarToUser() {
    }

    public CarToUser(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
