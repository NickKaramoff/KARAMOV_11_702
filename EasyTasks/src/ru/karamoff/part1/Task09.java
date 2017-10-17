package ru.karamoff.part1;

import java.util.Scanner;

public class Task09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите коэффициенты");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();


        if (a == 0 && b == 0 && c == 0) {
            System.out.println("0");
        } else {
            if (a != 0) {
                if (a == 1) {
                    System.out.print("x^2");
                } else if (a == -1) {
                    System.out.print("-x^2");
                } else {
                    System.out.print(a + "x^2");
                }

                if (b != 0) {
                    System.out.print((b > 0 ? " + " : " - "));
                } else if (c != 0) {
                    System.out.print((c > 0 ? " + " : " - "));

                }
            }

            if (b != 0) {
                if (b == 1 || b == -1) {
                    System.out.print("x");
                } else {
                    System.out.print((Math.abs(b) + "x"));
                }

                if (c != 0) {
                    System.out.print((c > 0 ? " + " : " - "));
                }
            }

            if (c != 0) {
                System.out.print(Math.abs(c));
            }
        }

    }
}
