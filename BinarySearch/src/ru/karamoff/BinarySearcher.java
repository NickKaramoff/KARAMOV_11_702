package ru.karamoff;

public class BinarySearcher {
    public static int search(int[] array, int start, int end, int toFind) {
        System.out.println("Поиск в диапазоне от " + start + " до " + end + " включительно...");
        int midPoint = start + (end - start) / 2;
        if (end - start > 0) {
            if (array[midPoint] > toFind) {
                System.out.println("Элемент в первой половине, сужаем диапазон");
                System.out.println();
                return search(array, start, midPoint, toFind);
            } else if (array[midPoint] < toFind) {
                System.out.println("Элемент во второй половине, сужаем диапазон");
                System.out.println();
                return search(array, midPoint, end, toFind);
            } else {
                System.out.println("Элемент найден! Индекс: " + midPoint);
                System.out.println();
                return midPoint;
            }
        } else {
            System.out.println("Элемент не найден!");
            System.out.println();
            return -1;
        }

    }
}
