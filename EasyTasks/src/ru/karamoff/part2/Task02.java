package ru.karamoff.part2;

import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a1 = scanner.nextInt();
        int a2 = scanner.nextInt();
        int k = scanner.nextInt();

        int result = a1 + (k - 1) * (a2 - a1);

        System.out.println(result);
    }
}
