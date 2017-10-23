package ru.karamoff.part2;

import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt() - 1;
        int result = (n + n * n) / 2;
        System.out.println(result);
    }
}
