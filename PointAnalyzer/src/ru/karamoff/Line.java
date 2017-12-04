package ru.karamoff;

import java.util.Arrays;

public class Line {
    private Point[] points;

    public Line(Point[] points) {
        this.points = points;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Point p : points){
            stringBuilder.append(p.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
