package ru.karamoff;

public class Airplane extends Vehicle {
    private int amountOfEngines;

    public Airplane(String brand, int power, Parking parking, int amountOfEngines) {
        super(brand, power, parking);
        this.amountOfEngines = amountOfEngines;
    }

    public int getAmountOfEngines() {
        return amountOfEngines;
    }
}
