package ru.karamoff.three;

public class Box extends ThreeDShape {
    private double height;
    private double width;
    private double length;

    public Box(double x, double y, double z, double height, double width, double length) {
        super(x, y, z);
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }


}
