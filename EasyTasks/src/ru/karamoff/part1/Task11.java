package ru.karamoff.part1;

import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты вершин треугольника \n в порядке: x_1, y_1, x_2, y_2, x_3, y_3");

        double x_1 = scanner.nextDouble();
        double y_1 = scanner.nextDouble();

        double x_2 = scanner.nextDouble();
        double y_2 = scanner.nextDouble();

        double x_3 = scanner.nextDouble();
        double y_3 = scanner.nextDouble();

        double a = Math.sqrt((x_2 - x_1) * (x_2 - x_1) + (y_2 - y_1) * (y_2 - y_1));
        double b = Math.sqrt((x_3 - x_2) * (x_3 - x_2) + (y_3 - y_2) * (y_3 - y_2));
        double c = Math.sqrt((x_3 - x_1) * (x_3 - x_1) + (y_3 - y_1) * (y_3 - y_1));
        double p = (a + b + c) / 2;

        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        System.out.println(s);
    }
}
