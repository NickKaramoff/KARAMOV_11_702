package ru.karamoff;

public class Point implements Comparable<Point> {

    private double x, y;

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(Point other) {
        return Double.compare(this.x, other.x);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
