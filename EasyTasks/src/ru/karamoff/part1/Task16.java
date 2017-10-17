package ru.karamoff.part1;

import java.util.Scanner;

public class Task16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        int numberM = number-1;
        int numberP = number+1;

        System.out.println(
                (numberP % 10 + numberP / 10 % 10 + numberP / 100 % 10 == numberP / 100000 + numberP / 10000 % 10 + numberP / 1000 % 10) ||
                        (numberM % 10 + numberM / 10 % 10 + numberM / 100 % 10 == numberM / 100000 + numberM / 10000 % 10 + numberM / 1000 % 10) ? "YES" : "NO"
        );
    }
}
