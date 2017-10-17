package ru.karamoff.part1;

import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите три отрезка");

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        System.out.println(a + b > c && a + c > b && b + c > a ? "YES" : "NO");
    }
}
