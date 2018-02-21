package ru.karamoff;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.println("Введите координаты начала и конца отрезка");
        Vector v = new Vector(
                new Vector.Point(4, 3),
                new Vector.Point(10, 5)
        );

        System.out.println("Введите координаты точки");
        Vector.Point p = new Vector.Point(scanner.nextDouble(), scanner.nextDouble());

        System.out.println(v.isHigher(p) ? "Выше" : "Ниже");
    }
}
