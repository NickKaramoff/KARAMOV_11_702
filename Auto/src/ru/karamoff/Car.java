package ru.karamoff;

public class Car extends Vehicle {
    private boolean hasSpareWheel;

    public Car(String brand, int power, Parking parking, boolean hasSpareWheel) {
        super(brand, power, parking);
        this.hasSpareWheel = hasSpareWheel;
    }

    public boolean hasSpareWheel() {
        return hasSpareWheel;
    }
}
