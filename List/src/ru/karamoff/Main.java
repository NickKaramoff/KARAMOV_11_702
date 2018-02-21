package ru.karamoff;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ExpandableArray array = new ExpandableArray();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Создать массив");
            System.out.println("2. Добавить число в конец массива");
            System.out.println("3. Удалить число из массива");
            System.out.println("4. Вставить число в массив");
            System.out.println("5. Вывести массив на экран");
            System.out.println("6. Выход");
            System.out.print("Выберите пункт меню: ");

            int choice = scanner.nextInt();

            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Введите количество элементов: ");

                    int n = scanner.nextInt();

                    System.out.println();
                    System.out.println("Введите числа в количестве " + n + ":");

                    while (array.count < n) {
                        array.appendItem(scanner.nextInt());
                    }

                    System.out.println("Массив длиной " + n + " создан.");
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Введите добавляемое число: ");

                    array.appendItem(scanner.nextInt());

                    System.out.println();
                    break;

                case 3:
                    System.out.print("Введите номер удаляемого элемента (от 1 до " + array.count + "): ");

                    array.removeItem(scanner.nextInt() - 1);

                    System.out.println();
                    break;

                case 4:
                    System.out.print("Введите номер вставляемого элемента (от 1 до " + array.count + "): ");

                    int index = scanner.nextInt() - 1;

                    System.out.print("Введите вставляемое число: ");

                    int element = scanner.nextInt();
                    array.insertItem(index, element);

                    System.out.println();
                    break;

                case 5:
                    array.printArray();
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Пожалуйста, введите номер от 1 до 6.");
                    System.out.println();
                    break;
            }
        }
    }
}
