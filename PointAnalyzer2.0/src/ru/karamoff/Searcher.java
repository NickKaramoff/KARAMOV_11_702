/*
 * created 18.12.2017 by nick
 *
 * If this code works, Nick Karamoff wrote it.
 * If this code doesn't work, I don't know, who wrote it
 */

package ru.karamoff;

import java.util.ArrayList;

public class Searcher {
	private Searcher() {}	// приватный конструктор по умолчанию — хороший ход для защиты от создания объектов

	public static int search(ArrayList<Line> lines, Point point, int start, int end) {
		int midPoint = start + (end - start) / 2;

		/*
			рассматривая последние точки линий, можно понять, что они будут отсортированы по убыванию
			соответственно, при бинарном поиске мы ищем в ПЕРВОЙ половине, когда рассматриваемая точка ВЫШЕ,
			и во ВТОРОЙ половине, когда рассматриваемая точка НИЖЕ

			когда выбор сужается до двух линий (start и end) мы просто проверяем положение рассматриваемовой точки
			относительно конечных точек этих двух линий

			если среди всех линий нет ни одной, конечная точка которой была бы ниже рассматриваемой, возвращается
			-1 как знак того, что нужно создать нужную линию
		*/
		if (end - start > 1) {
			if (point.getY() >= lines.get(midPoint).getLastPoint().getY()) {
				return search(lines, point, start, midPoint);
			} else {
				return search(lines, point, midPoint, end);
			}
		} else {
			if (point.getY() >= lines.get(start).getLastPoint().getY()) {
				return start;
			} else if (point.getY() >= lines.get(end).getLastPoint().getY()) {
				return end;
			} else {
				return -1;
			}
		}
	}
}
