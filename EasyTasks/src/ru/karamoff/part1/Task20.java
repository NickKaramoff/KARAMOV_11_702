package ru.karamoff.part1;

import java.util.Scanner;

public class Task20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = scanner.nextInt();
        int number2 = scanner.nextInt();


        System.out.println(
                (number1 % 10 + number2 % 10) % 10 + (number1 / 10 % 10 + number2 / 10 % 10) % 10 + (number1 / 100 % 10 + number2 / 100 % 10) % 10 == (number1 / 100000 + number2 / 100000) % 10 + (number1 / 10000 % 10 + number2 / 10000 % 10) % 10 + (number1 / 1000 % 10 + number2 / 1000 % 10) % 10
                        ? "YES" : "NO"
        );
    }
}
