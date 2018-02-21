package ru.karamoff;

public class Vector {
    private Point start, end;
    private double length, x, y;

    public Vector() {
        this.start = new Point();
        this.end = new Point();

        this.x = 0.0;
        this.y = 0.0;
        length = 0.0;
    }

    public Vector(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.x = end.x - start.x;
        this.y = end.y - start.y;

        this.length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public boolean isHigher(Point p) {
        Vector other = new Vector(start, p);

        double pseudoScalar = this.x * other.y - this.y * other.x;

        double sine = pseudoScalar / (this.length * other.length);
        return sine > 0;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return length;
    }

    public static class Point {

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
    }
}
