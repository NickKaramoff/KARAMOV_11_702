/*
 * created 18.12.2017 by nick
 *
 * If this code works, Nick Karamoff wrote it.
 * If this code doesn't work, I don't know, who wrote it
 */

package ru.karamoff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

	private final double WIDTH = 1080.0;
	private final double HEIGHT = 720.0;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("BinarySearch Powered PointAnalyzer");

		ArrayList<Point> points = new ArrayList<>();// коллекция всех точек
		ArrayList<Line> lines = new ArrayList<>();    // коллекция всех линий

		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String s = reader.readLine(); // считывание строки
			while (s != null) {
				String[] splitString = s.split("\\s+"); // деление строки надвое, учитывая возможные лишние пробелы
				double x = Double.parseDouble(splitString[0]);
				double y = Double.parseDouble(splitString[1]);
				points.add(new Point(x, y));
				s = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);	// аай, я маслину поймал!
		}

		Collections.sort(points);	// сортировка по x (см. Point) / O(N*log N)

		double maxX = points.get(points.size()-1).getX();
		double maxY = 0;

		for (Point point : points) {
			if (point.getY() > maxY) {
				maxY = point.getY();
			}
		}

		lines.add(new Line());                   // минимум одна точка => минимум одна линия
		lines.get(0).addPoint(points.get(0));    // самая первая по иксу точку обязательно стартует одну линию

		for (int i = 1; i < points.size(); i++) {
			Point observedPoint = points.get(i);// рассматриваем некую точку (в порядке возрастания икс)

			// бинарный поиск пробегается по существующим линиям, рассматривая максимальные по y крайние точки,
			// которые при этом меньше y-координаты рассматриваемой точки
			// метод возвращает индекс линии, куда можно присоединить точку, или -1, если таковой нет
			// сложность - O(log N)
			int lineNum = Searcher.search(lines, observedPoint, 0, lines.size() - 1);


			if (lineNum == -1) {
				lines.add(new Line());                                // при отсутствии линии создаём новую...
				lines.get(lines.size() - 1).addPoint(observedPoint);// ...и добавляем в неё рассматриваемую точку
			} else {
				lines.get(lineNum).addPoint(observedPoint);
			}
		}

		// сложность алгоритма: O(N*log N) + O(log N)*O(N) = O(N*log N)

		for (Line l :
				lines) {
			System.out.println(l.toString());	// вывод линий
		}

		Pane root = new Pane();

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext context = canvas.getGraphicsContext2D();

		double scale = calculateScale(maxX, maxY);

		for (Line line : lines) {
			drawLine(line, scale, context);
		}

		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.show();
	}

	private void drawLine(Line line, double scale, GraphicsContext gc) {
		gc.beginPath();
		double pointSize = 5.0;

		double x = line.getPoints().get(0).getX() * scale;
		double y = HEIGHT - line.getPoints().get(0).getY() * scale;

		gc.moveTo(x, y);
		gc.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
		for (int i = 1; i < line.getPoints().size(); i++) {
			x = line.getPoints().get(i).getX() * scale;
			y = HEIGHT - line.getPoints().get(i).getY() * scale;

			gc.lineTo(x, y);
			gc.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
		}
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
