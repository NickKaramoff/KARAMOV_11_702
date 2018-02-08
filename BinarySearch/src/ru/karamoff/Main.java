package ru.karamoff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
        int[] intArray = {63, 99, 31, 60, 54, 75, 3, 91, 38, 78, 44, 24, 18, 23, 5, 61, 40, 98, 30, 80};
        int intToFind = intArray[new Random().nextInt(intArray.length)];

        Arrays.sort(intArray);
        System.out.println("Отсортированный массив: " + Arrays.toString(intArray));
        System.out.println("Искомое число: " + intToFind);
        System.out.println();

        int intFoundIndex = BinarySearcher.search(intArray, 0, intArray.length - 1, intToFind);
        System.out.println();

		String[] stringsArray = {
				"Hello",
				"it's",
				"me",
				"I",
				"was",
				"wondering",
				"if",
				"after",
				"all",
				"these",
				"years",
				"you'd",
				"like",
				"to",
				"meet"
		};
//		String stringToFind = stringsArray[new Random().nextInt(stringsArray.length)];
		String stringToFind = "huypizda";

		System.out.println("Исходный массив: " + Arrays.toString(stringsArray));
		Arrays.sort(stringsArray);
		System.out.println("Отсортированный массив: " + Arrays.toString(stringsArray));
		System.out.println("Искомая строка: " + "\"" + stringToFind + "\"");
		System.out.println();

		int stringFoundIndex = BinarySearcher.search(stringsArray, 0, stringsArray.length - 1, stringToFind);
		System.out.println();
	}
}
