package ru.karamoff;

public class Line {
    private Point[] points;

    public Line(Point[] points) {
        this.points = points;
    }

    public int getLineLength() {
        return points.length;
    }

    public Point[] getPoints() {
        return points;
    }

    // выводит линию в виде последовательности точек
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Point p : points) {
            stringBuilder.append(p.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
