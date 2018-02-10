package ru.karamoff;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputA = scanner.next();
        String inputB = scanner.next();

        BigInteger a = new BigInteger(inputA);
        BigInteger b = new BigInteger(inputB);

        a.add(b);

        System.out.println(a.toString());
    }
}
