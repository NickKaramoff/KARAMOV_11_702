package ru.karamoff.part1;

import java.util.Scanner;

public class Task12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты вершин треугольника \nв порядке: x_1, y_1, x_2, y_2, x_3, y_3");

        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();

        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();

        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();

        System.out.println("Введите расстояния от точки до вершин \nв порядке: d_1, d_2, d_3");

        double d1 = scanner.nextDouble();
        double d2 = scanner.nextDouble();
        double d3 = scanner.nextDouble();


        // FIXME всё неправильно начиная с этой строки, выдаёт неправильный результат, мда



        double a = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)); // длина (1)(2)
        double b = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2)); // длина (2)(3)

        double eA = (a - (d1 + d2)) / 2 + d1; // длина отрезка от (1) до (6)
        double eB = (b - (d3 + d2)) / 2 + d2; // длина отрезка от (2) до (4)

        double xA = x1 + (eA / a) * (x2 - x1); // координаты точки срединного перпендикуляра из (3) к a
        double yA = y1 + (eA / a) * (y2 - y1);
        double xB = x2 + (eB / b) * (x3 - x2); // координаты точки срединного перпендикуляра из (1) к b
        double yB = y2 + (eB / b) * (y3 - y2);

        double kA = (yA - y3) / (xA - x3); // коэффициент уравнения прямой (a)->(3)
        double kB = (yB - y1) / (xB - x1); // коэффициент уравнения прямой (b)->(1)
        double mA = -(x3 * yA - xA * y3) / (xA - x3); // свободный член уравнения прямой (a)(3)
        double mB = -(x1 * yB - xB * y1) / (xB - x1); // свободный член уравнения прямой (b)(1)

        double x = (mB - mA) / (kA - kB); // координаты искомой точки
        double y = kA * x + mA;

        System.out.println("(" + x + ", " + y + ")");
    }
}
