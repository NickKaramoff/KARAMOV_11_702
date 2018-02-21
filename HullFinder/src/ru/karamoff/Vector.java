package ru.karamoff;

public class Vector {
    private Point start, end;
    private double length, x, y;

    public Vector(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.x = end.getX() - start.getX();
        this.y = end.getY() - start.getY();

        this.length = Math.sqrt(x * x + y * y);
    }

    public double pseudoScalar(Point p) {
        Vector other = new Vector(start, p);

        // определитель матрицы
        // | this.x     this.y  |
        // | other.x    other.y |
        return this.x * other.y - this.y * other.x;
    }

    public double pointSine(Point p) {
        return pseudoScalar(p) / (this.length * new Vector(start, p).length);
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

    @Override
    public String toString() {
        return start.toString() + "->" + end.toString();
    }
}
