package ru.karamoff.part1;

import java.util.Scanner;

public class Task12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты вершин треугольника \nв порядке: x_1, y_1, x_2, y_2, x_3, y_3");

        double x_1 = scanner.nextDouble();
        double y_1 = scanner.nextDouble();

        double x_2 = scanner.nextDouble();
        double y_2 = scanner.nextDouble();

        double x_3 = scanner.nextDouble();
        double y_3 = scanner.nextDouble();

        System.out.println("Введите расстояния от точки до вершин \nв порядке: d_1, d_2, d_3");

        double d_1 = scanner.nextDouble();
        double d_2 = scanner.nextDouble();
        double d_3 = scanner.nextDouble();

        double a = Math.sqrt((x_2 - x_1) * (x_2 - x_1) + (y_2 - y_1) * (y_2 - y_1)); // длина (1)(2)
        double b = Math.sqrt((x_3 - x_2) * (x_3 - x_2) + (y_3 - y_2) * (y_3 - y_2)); // длина (2)(3)

        double e_a = (a - (d_1 + d_2)) / 2 + d_1; // длина отрезка от (1) до (6)
        double e_b = (b - (d_3 + d_2)) / 2 + d_2; // длина отрезка от (2) до (4)

        double x_a = x_1 + (e_a / a) * (x_2 - x_1); // координаты точки срединного перпендикуляра из (3) к a
        double y_a = y_1 + (e_a / a) * (y_2 - y_1);
        double x_b = x_2 + (e_b / b) * (x_3 - x_2); // координаты точки срединного перпендикуляра из (1) к b
        double y_b = y_2 + (e_b / b) * (y_3 - y_2);

        double k_a = (y_a - y_3) / (x_a - x_3); // коэффициент уравнения прямой (a)->(3)
        double k_b = (y_b - y_1) / (x_b - x_1); // коэффициент уравнения прямой (b)->(1)
        double m_a = -(x_3 * y_a - x_a * y_3) / (x_a - x_3); // свободный член уравнения прямой (a)(3)
        double m_b = -(x_1 * y_b - x_b * y_1) / (x_b - x_1); // свободный член уравнения прямой (b)(1)

        double x = (m_b - m_a) / (k_a - k_b); // координаты искомой точки
        double y = k_a * x + m_a;

        System.out.println("(" + x + ", " + y + ")");
    }
}
