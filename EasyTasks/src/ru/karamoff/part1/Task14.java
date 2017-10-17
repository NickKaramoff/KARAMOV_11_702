package ru.karamoff.part1;

import java.util.Scanner;

public class Task14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        System.out.println(
                (number % 10 + number / 10 % 10 + number / 100 % 10 == number / 100000 + number / 10000 % 10 + number / 1000 % 10) ? "YES" : "NO"
        );
    }
}
