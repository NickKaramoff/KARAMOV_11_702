package ru.karamoff.part2;

import java.util.Scanner;

public class Task08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double number = scanner.nextDouble();
        long power = scanner.nextLong();

        System.out.println(toPower(number, power));
    }

    public static double toPower(double number, long power) {
        String binaryPowerString = Long.toBinaryString(power); // строка со степенью в двоичном виде
        long binaryPower = Long.parseLong(binaryPowerString); // число со степенью в двоичном виде

        double result = 1; // будущий результат

        for (int i = 0; i < binaryPowerString.length(); i++) {
            if (binaryPower % 10 == 1) {
                result *= number; // если цифра в двоичной записи равна 1, то домножаем
            }
            number *= number; // повышаем степень числа в любом случае
            binaryPower /= 10; // сокращаем степень на одну цифру
        }

        return result;
    }
}
