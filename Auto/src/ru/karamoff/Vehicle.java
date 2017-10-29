package ru.karamoff;

public class Vehicle {
    private String brand, color, number;
    private int power; // мощность в л.с.
    private int mileage = 0; // пробег в километрах
    private Parking parking;
    private int place = -1;

    public Vehicle(String brand, int power, Parking parking) {
        this.brand = brand;
        this.power = power;
        this.parking = parking;

        this.number = "a123aa";
        this.color = "black";
    }

    public Vehicle(String brand, String number, int power, String color, Parking parking) {
        this.brand = brand;
        this.number = number;
        this.power = power;
        this.color = color;
        this.parking = parking;
    }

    public void drive(int distance) {
        if (distance >= 0) {
            mileage += distance;
        } else {
            System.err.println("Неверное значение дистанции.");
        }
    }

    public void park() {
        parking.vehicleIn(this);
    }

    public void unpark() {
        parking.vehicleOut(this, place);
    }

    public boolean isParked(){
        return place != -1;
    }


    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public int getPower() {
        return power;
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

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
