package ru.karamoff.part1;

import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты вершин треугольника \n в порядке: x_1, y_1, x_2, y_2, x_3, y_3");

        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();

        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();

        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();

        double a = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double b = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
        double c = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));
        double p = (a + b + c) / 2;

        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        System.out.println(s);
    }
}
