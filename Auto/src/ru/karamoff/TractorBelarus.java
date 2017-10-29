package ru.karamoff;

public class TractorBelarus extends Vehicle {
    private double amountOfPotatoes;
    private boolean hasLukashenkoPortraitInside = true;

    public TractorBelarus(String brand, int power, Parking parking, double amountOfPotatoes) {
        super(brand, power, parking);
        this.amountOfPotatoes = amountOfPotatoes;
    }

    public double getAmountOfPotatoes() {
        return amountOfPotatoes;
    }

    public boolean hasLukashenkoPortraitInside() {
        return hasLukashenkoPortraitInside;
    }
}
