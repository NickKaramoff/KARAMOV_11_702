package ru.karamoff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

    private final double WIDTH = 1080.0;
    private final double HEIGHT = 720.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Nikita Karamov Ultimate PointAnalyzerFX Hyper 3.0 [Gold Premium Version] - REGISTERED");

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

        Collections.sort(points);

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
        }

        Pane root = new Pane();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();

        double scale = calculateScale(11.9, 7.8); // TODO: find max x and y

        for (Line line : lines) {
            drawLine(line, scale, context);
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    void drawLine(Line line, double scale, GraphicsContext gc) {
        gc.beginPath();
        double pointSize = 5.0;

        double x = line.getPoints()[0].getX() * scale;
        double y = HEIGHT - line.getPoints()[0].getY() * scale;

        gc.moveTo(x, y);
        gc.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
        for (int i = 1; i < line.getLineLength(); i++) {
            x = line.getPoints()[i].getX() * scale;
            y = HEIGHT - line.getPoints()[i].getY() * scale;

            gc.lineTo(x, y);
            gc.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
        }
        gc.stroke();
    }

    double calculateScale(double maxX, double maxY) {
        double margin = 0.05 * (maxX > maxY ? maxX : maxY);
        maxX += margin;
        maxY += margin;

        if (maxX / maxY < WIDTH / HEIGHT) {
            maxX = maxY * (WIDTH / HEIGHT);
        }

        return WIDTH / maxX;
    }
}
