package ru.karamoff;

public class Point implements Comparable<Point> {
    private double x, y;
    private double tan;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
//        this.tan = (x != 0) ? (this.y / this.x) : Double.MAX_VALUE;
    }

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

//    public double getTan() {
//        return Math.atan((this.y / this.x));
//    }

    @Override
    public int compareTo(Point o) {

        return Double.compare(this.x, o.x);
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
