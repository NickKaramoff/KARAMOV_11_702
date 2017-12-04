package ru.karamoff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("points.txt"));
            String s;
            while ((s = reader.readLine()) != null) {
                String[] splitString = s.split(" ");
                double x = Double.parseDouble(splitString[0]);
                double y = Double.parseDouble(splitString[1]);
                points.add(new Point(x, y));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(points);

        while (points.size() > 0) {
            int i = 0;
            Point startPoint = new Point();
            ArrayList<Point> linePoints = new ArrayList<>();

            do {
                if (points.get(i).getX() >= startPoint.getX() &&
                        points.get(i).getY() >= startPoint.getY()) {

                    startPoint = points.get(i);

                    linePoints.add(startPoint);

                    points.remove(i);
                    i--;
                }
                i++;
            } while (i < points.size());

            lines.add(new Line(linePoints.toArray(new Point[linePoints.size()])));
        }

        for (Line l :
                lines) {
            System.out.println(l.toString());
        }
    }
}
