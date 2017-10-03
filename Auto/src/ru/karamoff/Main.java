package ru.karamoff;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Car> cars = new ArrayList<>();
        Parking parking = new Parking();

        while (true) {
            System.out.println("1. Добавить машину");
            System.out.println("2. Удалить машину");
            System.out.println("3. Отобразить все машины");
            System.out.println("4. Припарковать машину");
            System.out.println("5. Снять машину с парковки");
            System.out.println("6. Информация о парковке");
            System.out.println("7. Выход");
            System.out.print("Выберите пункт меню: ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Введите модель: ");
                    String brand = scanner.next();
                    System.out.print("Введите номер: ");
                    String number = scanner.next();
                    System.out.print("Введите мощность: ");
                    int power = scanner.nextInt();
                    System.out.print("Введите цвет: ");
                    String color = scanner.next();

                    cars.add(new Car(brand, number, power, color, parking));

                    System.out.println();
                    break;
                case 2:
                    System.out.print("Введите номер машины: ");
                    cars.remove(scanner.nextInt() - 1);
                    System.out.println();
                    break;
                case 3:
                    for (int i = 0; i < cars.size(); i++) {
                        System.out.println(i + 1 + ". " + cars.get(i).getBrand() + ", " + cars.get(i).getColor() + ", " + cars.get(i).getPower() + " л. с., " + cars.get(i).getNumber());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Введите номер машины: ");
                    cars.get(scanner.nextInt() - 1).park();
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Введите номер машины: ");
                    cars.get(scanner.nextInt() - 1).unpark();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Сейчас припарковано " + parking.getFullness() + " машин.");
                    parking.listCars();
                    System.out.println();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
