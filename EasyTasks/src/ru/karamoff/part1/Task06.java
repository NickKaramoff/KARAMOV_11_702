package ru.karamoff.part1;

import java.util.Scanner;

public class Task06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите год");

        int year = scanner.nextInt();

        System.out.println(year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? "12/10/" + toFour(year) : "13/10/" + toFour(year));
    }

    /**
     * Outputs year in 4-digit format. Puts zero in front of years less than 1000
     * @param year year in form of an integer
     * @return Returns a string containing year in 4-digit format
     */
    private static String toFour(int year) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year);

        while (stringBuilder.length() != 4) {
            stringBuilder = stringBuilder.insert(0, "0");
        }

        return stringBuilder.toString();
    }

}
