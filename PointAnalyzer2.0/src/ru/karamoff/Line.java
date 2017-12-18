/*
 * created 18.12.2017 by nick
 *
 * If this code works, Nick Karamoff wrote it.
 * If this code doesn't work, I don't know, who wrote it
 */

package ru.karamoff;

import java.util.ArrayList;

public class Line {
    private ArrayList<Point> points;

    public Line(ArrayList<Point> points) {
        this.points = points;
    }

	public Line() {
    	points = new ArrayList<>();
	}

	public void addPoint(Point point) {
        this.points.add(point);
    }

    // метод сразу возвращает последнюю точку; это удобнее, чем искать через size()
    public Point getLastPoint() {
        return this.points.get(points.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Point p : points) {
            stringBuilder.append(p.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
