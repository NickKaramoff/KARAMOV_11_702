package ru.karamoff.part1;

import java.util.Scanner;

public class Task08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double r1 = scanner.nextDouble();

        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double r2 = scanner.nextDouble();

        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        System.out.println(distance < r1 + r2 ? "YES" : "NO");
    }
}
