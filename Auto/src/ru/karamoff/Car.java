package ru.karamoff;

public class Car {
    private String brand, color, number;
    private int power; // мощность в л.с.

    private int mileage = 0; // пробег в километрах

    private Parking parking;

    public Car() {

    }

    public Car(String brand, int power) {
        this.brand = brand;
        this.power = power;
    }

    public Car(String brand, String number, int power, String color, Parking parking) {
        this.brand = brand;
        this.number = number;
        this.power = power;
        this.color = color;
        this.parking = parking;
    }

    void drive(int distance) {
        if (distance >= 0) {
            mileage += distance;
        } else {
            System.err.println("Неверное значение дистанции.");
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power > 0) {
            this.power = power;
        } else {
            System.err.println("Неправильное значение");
            System.err.println("Установлено значение по умолчанию");
            this.power = 0;

        }
    }

    public int getMileage() {
        return mileage;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public void park() {
        parking.takeCar(this);
    }

    public void unpark() {
        parking.releaseCar(this);
    }
}
