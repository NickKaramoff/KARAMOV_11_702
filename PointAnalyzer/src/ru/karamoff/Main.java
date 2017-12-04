package ru.karamoff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();// коллекция всех точек из документа
        ArrayList<Line> lines = new ArrayList<>();  // коллекция всех полученных линий

        try {
            BufferedReader reader = new BufferedReader(new FileReader("points.txt"));
            String s = reader.readLine(); // считывание строки

            while (s != null) {
                String[] splitString = s.split("\\s+"); // деление на x и y, учитываются возможные лишние пробелы
                double x = Double.parseDouble(splitString[0]);
                double y = Double.parseDouble(splitString[1]);
                points.add(new Point(x, y));
                s = reader.readLine();
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(points); // сортировка: O(n*logn)

        while (points.size() > 0) {
            int i = 0;
            Point startPoint = new Point();                     // начальная точка - (0;0)
            ArrayList<Point> linePoints = new ArrayList<>();    // коллекция точек будущей линии

            do {
                if (points.get(i).getX() >= startPoint.getX() &&
                        points.get(i).getY() >= startPoint.getY()) {
                    startPoint = points.get(i); // новая точка становится начальной
                    linePoints.add(startPoint); // новая точка добавляется в линию
                    points.remove(i);           // новая точка удаляется из коллекции всех точек
                    i--;                        // счётчик уменьшается, чтобы учесть удаление точки
                }
                i++;
            } while (i < points.size());

            lines.add(new Line(linePoints.toArray(new Point[linePoints.size()]))); // добавление линии в коллекцию
        } // поиск линий: O(n^2)

        for (Line l :
                lines) {
            System.out.println(l.toString());
        }
    }
}
