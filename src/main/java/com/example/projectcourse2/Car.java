package com.example.projectcourse2;

public class Car {
    private String brand;
    private String number;
    private String carClass;
    public Car (){}

    public Car(String brand, String number, String carClass) {
        this.brand = brand;
        this.number = number;
        this.carClass = carClass;
    }

    public String getBrand() {
        return brand;
    }

    public String getNumber() {
        return number;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }
}

