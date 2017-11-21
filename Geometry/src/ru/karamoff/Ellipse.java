package ru.karamoff;

public class Ellipse extends Shape {
    private double radius1, radius2;

    public Ellipse(double radius1, double radius2) {
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    public double area() {
        return Math.PI* radius1 * radius2;
    }

    public double perimeter() {
        return 4*(Math.PI* radius1 * radius2 +(radius1 - radius2)*(radius1 - radius2))/(radius1 + radius2);
    }
}
