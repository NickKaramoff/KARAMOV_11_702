package ru.karamoff.part1;

import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координаты шахматной клетки");

        String coordinates = scanner.next();
        int letter = coordinates.charAt(0) - 'a' + 1;
        int number = coordinates.charAt(1) - '0';

        System.out.println((letter + number) % 2 == 0 ? "BLACK" : "WHITE");
    }
}
