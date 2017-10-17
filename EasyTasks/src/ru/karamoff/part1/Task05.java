package ru.karamoff.part1;

import java.util.Scanner;

public class Task05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координаты шахматных клеток");

        String coordinates1 = scanner.next();
        String coordinates2 = scanner.next();

        int letter1 = coordinates1.charAt(0) - 'a' + 1;
        int number1 = coordinates1.charAt(1) - '0';
        int letter2 = coordinates2.charAt(0) - 'a' + 1;
        int number2 = coordinates2.charAt(1) - '0';

        if (letter1 + number1 == letter2 + number2) {
            System.out.println("SAME");
        } else if (Math.abs(letter1 + number1 - (letter2 + number2)) == 1) {
            System.out.println("NEIGHBOUR");
        }
    }
}
