package ru.karamoff.part1;

import java.util.Scanner;

public class Task20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number1 = scanner.next();
        String number2 = scanner.next();

        if (number1.length() == 6 && number2.length() == 6) {

            int[] number = new int[6];
            for (int i = 0; i < 6; i++) {
                number[i] = ((number1.charAt(i) - '0') + (number2.charAt(i) - '0')) % 10;
            }

            System.out.println(
                    number[0] + number[1] + number[2] == number[3] + number[4] + number[5] ? "YES" : "NO"
            );
        } else {
            System.err.println("Введите два шестизначных числа");
        }
    }
}
