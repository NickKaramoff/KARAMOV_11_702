package ru.karamoff;

import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координаты шахматных клеток");

        String coordinates1 = scanner.next();
        String coordinates2 = scanner.next();

        int letter1 = coordinates1.charAt(0) - 'a' + 1;
        int number1 = coordinates1.charAt(1) - '0';
        int letter2 = coordinates2.charAt(0) - 'a' + 1;
        int number2 = coordinates2.charAt(1) - '0';

        if (Math.abs(letter2-letter1)==2 && Math.abs(number2-number1)==1 ||
                Math.abs(letter2-letter1)==1 && Math.abs(number2-number1)==2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
