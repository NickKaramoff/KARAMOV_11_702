package ru.karamoff;

public class SportCar extends Car {
    private double fuelConsumptionPer100;

    public SportCar(String brand, int power, Parking parking, boolean hasSpareWheel, double fuelConsumptionPer100) {
        super(brand, power, parking, hasSpareWheel);
        this.fuelConsumptionPer100 = fuelConsumptionPer100;
    }

    public double getFuelConsumptionPer100() {
        return fuelConsumptionPer100;
    }
}
