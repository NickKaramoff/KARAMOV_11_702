package ru.karamoff;

public class Tank extends Vehicle{
    private double cannonLength;

    public Tank(String brand, int power, Parking parking, double cannonLength) {
        super(brand, power, parking);
        this.cannonLength = cannonLength;
    }

    public double getCannonLength() {
        return cannonLength;
    }
}
