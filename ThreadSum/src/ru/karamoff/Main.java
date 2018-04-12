package ru.karamoff;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }

        int forSum = 0;

        long startTime = System.currentTimeMillis();
        for (int number : array) {
            forSum += number;
        }
        long endTime = System.currentTimeMillis();


        System.out.println("Посчитано циклом: " + forSum + " за " + (endTime-startTime) + " мс");

        int threadSum = 0;
        AddingThread[] threads = new AddingThread[k];

        startTime = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new AddingThread((n / k) * i, (n / k) * (i + 1), array);
            threads[i].start();
            threads[i].join();

            threadSum += threads[i].getSum();
        }
        endTime = System.currentTimeMillis();

        System.out.println("Посчитано потоками: " + threadSum + " за " + (endTime-startTime) + " мс");
        System.out.println("Суммы " + (forSum == threadSum ? "" : "не ") + "равны");
    }
}
