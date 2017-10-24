package ru.karamoff.three;

public class Sphere extends ThreeDShape {
    private double radius;

    public Sphere(double x, double y, double z, double radius) {
        super(x, y, z);
        this.radius = radius;
    }



    public double getRadius() {
        return radius;
    }

    public void scale(double value) {
        this.radius *= value;
    }
}
