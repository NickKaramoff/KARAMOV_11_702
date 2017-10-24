package ru.karamoff.three;

public class ThreeDShape {
    private double x;
    private double y;
    private double z;

    public ThreeDShape(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void move(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // не выполняется ничего
    public void scale(double value) {
        return;
    }
}
