package ru.karamoff.part2;

import java.util.Scanner;

public class Task04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int f = scanner.nextInt();
        int s = scanner.nextInt();
        int n = scanner.nextInt();

        double check = 1.0 + (double) (n - f) / s;

        if ((check % 1) != 0) {
            System.out.println("-1");
        } else {
            System.out.println((int) check);
        }
    }
}
