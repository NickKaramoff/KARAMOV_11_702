package ru.karamoff.part2;

import java.util.Scanner;

public class Task05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a1 = scanner.nextDouble();
        double a2 = scanner.nextDouble();
        double d = scanner.nextDouble();

        double between = (a2 - a1) / d - 1;

        System.out.println((int)between + " членов прогрессии между " + a1 + " и " + a2);

        double sum = 0.0;
        a1 += d;
        while (a1 < a2) {
            sum += a1;
            a1 += d;
        }

        System.out.println("Сумма промежуточных членов равна " + sum);
    }
}
