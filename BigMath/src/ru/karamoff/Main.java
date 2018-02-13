package ru.karamoff;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputA = scanner.next();
        String inputB = scanner.next();

        BigInteger a = new BigInteger(inputA);
        BigInteger b = new BigInteger(inputB);

        BigInteger s = BigInteger.sum(a, b);
        BigInteger m = BigInteger.product(a, b);

        System.out.println(a.toString() + " + " + b.toString() + " = " + s.toString());
        System.out.println(a.toString() + " * " + b.toString() + " = " + m.toString());

        switch (a.compareTo(b)) {
            case 1:
                System.out.println(a.toString() + " > " + b.toString());
                break;
            case 0:
                System.out.println(a.toString() + " = " + b.toString());
                break;
            case -1:
                System.out.println(a.toString() + " < " + b.toString());
                break;
        }
    }
}
