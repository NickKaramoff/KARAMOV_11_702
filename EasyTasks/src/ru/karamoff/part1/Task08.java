package ru.karamoff.part1;

import java.util.Scanner;

public class Task08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x_1 = scanner.nextDouble();
        double y_1 = scanner.nextDouble();
        double r_1 = scanner.nextDouble();

        double x_2 = scanner.nextDouble();
        double y_2 = scanner.nextDouble();
        double r_2 = scanner.nextDouble();

        double distance = Math.sqrt((x_2 - x_1) * (x_2 - x_1) + (y_2 - y_1) * (y_2 - y_1));

        System.out.println(distance < r_1 + r_2 ? "YES" : "NO");
    }
}
