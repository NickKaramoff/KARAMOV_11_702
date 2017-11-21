package ru.karamoff.part2;

public class Task07 {
    public static void main(String[] args) {
        int sumOfSquares = 0;
        int squareOfSum = 0;

        for (int i = 1; i <= 100; i++) {
            sumOfSquares += i * i;
            squareOfSum += i;
        }
        squareOfSum *= squareOfSum;

        System.out.println(Math.abs(squareOfSum-sumOfSquares));
    }
}
