package ru.karamoff.part1;

import java.util.Scanner;

public class Task13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.next();

        if (number.length() == 6) {
            int[] numbers = new int[6];

            for (int i = 0; i < 6; i++) {
                numbers[i] = number.charAt(i) - '0';
            }

            System.out.println(
                    numbers[5] == numbers[0] && numbers[4] == numbers[1] && numbers[3] == numbers[2] ? "YES" : "NO"
            );
        } else {
            System.err.println("Введите шестизначное число!");
        }
    }
}
