/*
 * created 18.12.2017 by nick
 *
 * If this code works, Nick Karamoff wrote it.
 * If this code doesn't work, I don't know, who wrote it
 */

package ru.karamoff;

public class Point implements Comparable<Point> {
    private double x = 0.0;
    private double y = 0.0;

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
    public int compareTo(Point o) {
        return Double.compare(this.x, o.x);
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
