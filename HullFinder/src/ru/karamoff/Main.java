package ru.karamoff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;


public class Main extends Application {

    private final double WIDTH = 1080.0;
    private final double HEIGHT = 720.0;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HullFinder");


        ArrayList<Point> p = new ArrayList<>();

        p.add(new Point(0, 6));
        p.add(new Point(1, 9));
        p.add(new Point(5, 13));
        p.add(new Point(14, 12));
        p.add(new Point(18, 7));
        p.add(new Point(16, 3));
        p.add(new Point(13, 0));
        p.add(new Point(8, 1));
        p.add(new Point(3, 2));

        p.add(new Point(4, 10));
        p.add(new Point(4, 6));
        p.add(new Point(8, 5));
        p.add(new Point(11, 2));
        p.add(new Point(12, 5));
        p.add(new Point(13, 8));
        p.add(new Point(7, 9));
        p.add(new Point(10, 9));
        p.add(new Point(11, 11));

        Hull h = new Hull(p);

        ArrayList<Point> top = new ArrayList<>();
        ArrayList<Point> bottom = new ArrayList<>();

        split(h.getHull(), top, bottom);

        double maxX = h.getHull().get(h.getHull().size() - 1).getX();
        double maxY = 0;

        for (Point point : h.getHull()) {
            if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }

        Pane root = new Pane();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();

        double scale = calculateScale(maxX, maxY);

        for (int i = 0; i < h.getPoints().size(); i++) {
            double x = h.getPoints().get(i).getX() * scale;
            double y = HEIGHT - h.getPoints().get(i).getY() * scale;

            context.moveTo(x, y);
            context.fillOval(x - 5.0 / 2, y - 5.0 / 2, 5.0, 5.0);
        }

        for (int i = 0; i < top.size() - 1; i++) {
            drawLine(top.get(i), top.get(i + 1), scale, context);
        }

        for (int i = 0; i < bottom.size() - 1; i++) {
            drawLine(bottom.get(i), bottom.get(i + 1), scale, context);
        }

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    private void split(ArrayList<Point> hull,
                       ArrayList<Point> top,
                       ArrayList<Point> bottom) {

        Point start = hull.get(0);
        Point end = hull.get(1);
        double tan = (end.getY() - start.getY()) / (end.getX() - start.getX());
        top.add(start);
        top.add(end);
        bottom.add(start);
        bottom.add(end);

        for (int i = 2; i < hull.size(); i++) {
            Point p = hull.get(i);
            if ((p.getY() - start.getY()) / (p.getX() - start.getX()) >= tan) {
                top.add(p);
            } else {
                bottom.add(p);
            }
        }

        Collections.sort(top);
        Collections.sort(bottom);
        Collections.reverse(bottom);
    }

    private void drawLine(Point p1, Point p2, double scale, GraphicsContext gc) {
        gc.beginPath();
        double pointSize = 5.0;

        double x = p1.getX() * scale;
        double y = HEIGHT - p1.getY() * scale;

        gc.moveTo(x, y);
        gc.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);


        x = p2.getX() * scale;
        y = HEIGHT - p2.getY() * scale;

        gc.lineTo(x, y);
        gc.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);


        gc.stroke();
    }

    private double calculateScale(double maxX, double maxY) {
        double margin = 0.05 * (maxX > maxY ? maxX : maxY);
        maxX += margin;
        maxY += margin;

        if (maxX / maxY < WIDTH / HEIGHT) {
            maxX = maxY * (WIDTH / HEIGHT);
        }

        return WIDTH / maxX;
    }
}
