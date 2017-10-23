package ru.karamoff.part1;

import java.util.Scanner;

public class Task19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number1 = scanner.next();
        String number2 = scanner.next();

        if (number1.length() == 4 && number2.length() == 4) {

            int[] numbers1 = new int[4];
            for (int i = 0; i < 4; i++) {
                numbers1[i] = number1.charAt(i) - '0';
            }

            int[] numbers2 = new int[4];
            for (int i = 0; i < 4; i++) {
                numbers2[i] = number2.charAt(i) - '0';
            }

            int count = 0;

            if (numbers1[0] == numbers2[1] || numbers1[0] == numbers2[2] || numbers1[0] == numbers2[3]) {
                count++;
            }
            if (numbers1[1] == numbers2[0] || numbers1[1] == numbers2[2] || numbers1[1] == numbers2[3]) {
                count++;
            }
            if (numbers1[2] == numbers2[0] || numbers1[2] == numbers2[1] || numbers1[2] == numbers2[3]) {
                count++;
            }
            if (numbers1[3] == numbers2[0] || numbers1[3] == numbers2[1] || numbers1[3] == numbers2[2]) {
                count++;
            }

            System.out.println("Количество коров: " + count);
        } else {
            System.err.println("Введите два четырёхзначных числа!");
        }
    }
}
