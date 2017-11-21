package ru.karamoff.part2;

import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        while (number != 0) {
            System.out.print(number%2);
            number /= 2;
        }

        System.out.println();
    }
}
