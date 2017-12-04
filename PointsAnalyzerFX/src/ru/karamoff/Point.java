package ru.karamoff;

public class Point implements Comparable<Point> { // использует интерфейс для сравнения
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
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

    // метод, сравнивающий x рассматриваемой точки с x другой точки
    @Override
    public int compareTo(Point o) {
        return Double.compare(this.x, o.x);
    }

    // выводит точку в формате (x; y)
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
