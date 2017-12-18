package ru.karamoff;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] testArray = {63, 99, 31, 60, 54, 75, 3, 91, 38, 78, 44, 24, 18, 23, 5, 61, 40, 98, 30, 80};
        int numberToFind = testArray[new Random().nextInt(testArray.length)];

        Arrays.sort(testArray);
        System.out.println("Отсортированный массив: " + Arrays.toString(testArray));
        System.out.println("Искомое число: " + numberToFind);
        System.out.println();

        int a = BinarySearcher.search(testArray, 0, testArray.length - 1, numberToFind);
//        System.out.println();
    }
}
