package ru.karamoff.part1;

import java.util.Scanner;

public class Task18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = scanner.nextInt();
        int number2 = scanner.nextInt();
        int count = 0;

        if (number1 % 10 == number2 % 10) {
            count++;
        }
        if (number1 / 10 % 10 == number2 / 10 % 10) {
            count++;
        }
        if (number1 / 100 % 10 == number2 / 100 % 10) {
            count++;
        }
        if (number1 / 1000 % 10 == number2 / 1000 % 10) {
            count++;
        }

        System.out.println("Количество быков: " + count);
    }
}
