/*
 * created 18.12.2017 by nick
 *
 * If this code works, Nick Karamoff wrote it.
 * If this code doesn't work, I don't know, who wrote it
 */

package ru.karamoff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
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
	}
}
