package ru.karamoff.part1;

import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите коэффициенты");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        if (a != 0) {
            int d = b * b - 4 * a * c;
            if (d > 0) {
                System.out.println("Два действительных корня:");
                double x_1 = (-b + Math.sqrt(d)) / (2 * a);
                double x_2 = (-b - Math.sqrt(d)) / (2 * a);
                System.out.println(x_1);
                System.out.println(x_2);
            } else if (d == 0) {
                System.out.println("Один действительный корень:");
                double x = (-b) / (2 * a);
                System.out.println(x);
            } else {
                System.out.println("Два комплексных корня:");
                System.out.println((-b / (2 * a)) + " ± " + (Math.sqrt(Math.abs(d)) / (2 * a)) + "i");
            }
        } else {
            System.err.println("Первый коэффициент не может равняться нулю!");
        }
    }
}
