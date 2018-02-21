package ru.karamoff;

import java.util.ArrayList;
import java.util.Collections;

public class Hull {
    private ArrayList<Point> points;
    private ArrayList<Point> hull;

    public Hull(ArrayList<Point> points) {
        this.points = points;
        this.hull = new ArrayList<>();

        Collections.sort(this.points);

        Point left = this.points.get(0);
        Point right = this.points.get(this.points.size() - 1);
        hull.add(left);
        hull.add(right); // крайние точки однозначно входят в оболочку

        findInSplit(new Vector(left, right)); // ищет в верхней половинке
        findInSplit(new Vector(right, left)); // ищет в нижней половинке
    }

    private void findInSplit(Vector v) {
        Point start = v.getStart();
        Point end = v.getEnd();

        double maxArea = 0.0;      // определяем максимальный треугольник как треугольник площади 0,
        Point maxPoint = v.getEnd(); // который состоит из точек start, end и end

        for (Point p : points) {
            if (!p.equals(start) && !p.equals(end)) { //не проверяем start и end

                double sine = v.pointSine(p);

                if (sine >= 0) { // рассматриваем только "верхние" точки

                    //площадь треугольника по формуле с синусом
                    double area = 0.5 * v.pseudoScalar(p);

                    Vector pVector = new Vector(start, p);
                    if (area > maxArea || /* если площадь треугольника больше, то точка дальше */
                            (area == maxArea
                                    && pVector.getLength() < new Vector(start, maxPoint).getLength())) {
                        /* если нашли точку с такой же площадью треугольника, то берём ближайшую
                         * к началу точку (измеряя длины векторов от start к p и к maxPoint */

                        maxArea = area;
                        maxPoint = p;
                    }
                }
            }
        }

        if (!maxPoint.equals(v.getEnd())) {                  // если в процессе мы нашли точку
            hull.add(maxPoint);                              // то добавляем её в оболочку
            findInSplit(new Vector(v.getStart(), maxPoint)); // запускаем поиск от start к точке
            findInSplit(new Vector(maxPoint, v.getEnd()));   // и от точки к end
        }
    }

    public ArrayList<Point> getHull() {
        return hull;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Point p : hull) {
            builder.append(p.toString()).append("\n");
        }
        return builder.toString();
    }
}
