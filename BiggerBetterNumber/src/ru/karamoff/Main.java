package ru.karamoff;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        while (number != 0) {
            list.add(number % 10);
            number /= 10;
        }


        int result = 0;

        for (int i = 1; i < list.size(); i++) {

            for (int j = i-1; j >= 0; j--) {
                if (list.get(j) > list.get(i)) {
                    result += fact(i);
                }
            }

        }

        System.out.println(result);
    }

    public static int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return fact(n - 1) * n;
        }
    }
}
