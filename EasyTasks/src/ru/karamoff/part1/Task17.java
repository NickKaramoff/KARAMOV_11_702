package ru.karamoff.part1;

import java.util.Scanner;

public class Task17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.next();

        if (number.length() == 6) {
            int[] numbers = new int[6];

            for (int i = 0; i < 6; i++) {
                numbers[i] = number.charAt(i) - '0';
            }

            int[] numberPlus = Task16.plusOne(numbers);
            int[] numberMinus = Task16.minusOne(numbers);



            System.out.println(
                    (numberPlus[0] + numberPlus[2] + numberPlus[4] == numberPlus[1] + numberPlus[3] + numberPlus[5]) ||
                            (numberMinus[0] + numberMinus[2] + numberMinus[4] == numberMinus[1] + numberMinus[3] + numberMinus[5]) ? "YES" : "NO"
            );
        } else {
            System.err.println("Введите шестизначное число!");
        }
    }
}
