package ru.karamoff.part1;

import java.util.Scanner;

public class Task16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.next();

        if (number.length() == 6) {
            int[] numbers = new int[6];

            for (int i = 0; i < 6; i++) {
                numbers[i] = number.charAt(i) - '0';
            }

            int[] numberPlus = plusOne(numbers);
            int[] numberMinus = minusOne(numbers);



            System.out.println(
                    (numberPlus[0] + numberPlus[1] + numberPlus[2] == numberPlus[3] + numberPlus[4] + numberPlus[5]) ||
                            (numberMinus[0] + numberMinus[1] + numberMinus[2] == numberMinus[3] + numberMinus[4] + numberMinus[5]) ? "YES" : "NO"
            );
        } else {
            System.err.println("Введите шестизначное число!");
        }
    }

    static int[] plusOne(int[] number) {
        number[5] += 1;
        if (number[5] == 10) {
            number[5] = 0;
            number[4] += 1;
            if (number[4] == 10) {
                number[4] = 0;
                number[3] += 1;
                if (number[3] == 10) {
                    number[3] = 0;
                    number[2] += 1;
                    if (number[2] == 10) {
                        number[2] = 0;
                        number[1] += 1;
                        if (number[1] == 10) {
                            number[1] = 0;
                            number[0] += 1;
                            if (number[0] == 10) {
                                number[0] = 0;
                            }
                        }
                    }
                }
            }
        }
        return number;
    }

    static int[] minusOne(int[] number) {
        number[5] -= 1;
        if (number[5] == -1) {
            number[5] = 9;
            number[4] -= 1;
            if (number[4] == -1) {
                number[4] = 9;
                number[3] -= 1;
                if (number[3] == -1) {
                    number[3] = 9;
                    number[2] -= 1;
                    if (number[2] == -1) {
                        number[2] = 9;
                        number[1] -= 1;
                        if (number[1] == -1) {
                            number[1] = 9;
                            number[0] -= 1;
                            if (number[0] == -1) {
                                number[0] = 9;
                            }
                        }
                    }
                }
            }
        }
        return number;
    }
}
