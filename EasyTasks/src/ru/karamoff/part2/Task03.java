package ru.karamoff.part2;

import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a1 = scanner.nextInt();
        int a2 = scanner.nextInt();
        int n = scanner.nextInt();

        int result = n*(2*a1+(a2-a1)*(n-1))/2;

        System.out.println(result);
    }
}
