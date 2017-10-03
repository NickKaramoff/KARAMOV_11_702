package ru.karamoff;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Car> cars = new ArrayList<>();
//        Parking parking = new Parking();
        ArrayList<Parking> parkings = new ArrayList<>();


        System.out.println("Парковочный робот 1.0\n");

        while (true) {
            System.out.println("1. Добавить парковку");
            System.out.println("2. Удалить парковку");
            System.out.println("3. Информация о парковках");
            System.out.println("4. Добавить машину");
            System.out.println("5. Удалить машину");
            System.out.println("6. Отобразить все машины");
            System.out.println("7. Припарковать машину");
            System.out.println("8. Снять машину с парковки");
            System.out.println("9. Выход");
            System.out.print("Выберите пункт меню: ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Введите время начала (ч): ");
                    LocalTime openTime = LocalTime.of(scanner.nextInt(), 0);
                    System.out.print("Введите время конца (ч): ");
                    LocalTime closeTime = LocalTime.of(scanner.nextInt(), 0);
                    System.out.print("Введите вместимость: ");
                    int capacity = scanner.nextInt();

                    parkings.add(new Parking(openTime, closeTime, capacity));

                    break;
                case 2:
                    System.out.print("Введите номер парковки: ");
                    cars.remove(scanner.nextInt() - 1);
                    break;
                case 3:
                    System.out.println("Парковки:");
                    for (Parking mParking : parkings) {
                        System.out.println((parkings.indexOf(mParking) + 1) + ". " + mParking.getFullness() + "/" + mParking.getCapacity());
                    }
                    break;
                case 4:
                    System.out.print("Введите модель: ");
                    String brand = scanner.next();
                    System.out.print("Введите номер: ");
                    String number = scanner.next();
                    System.out.print("Введите мощность: ");
                    int power = scanner.nextInt();
                    System.out.print("Введите цвет: ");
                    String color = scanner.next();
                    System.out.print("Введите номер парковки: ");
                    Parking parking = parkings.get(scanner.nextInt() - 1);

                    cars.add(new Car(brand, number, power, color, parking));

                    break;
                case 5:
                    System.out.print("Введите номер машины: ");
                    cars.remove(scanner.nextInt() - 1);
                    break;
                case 6:
                    for (int i = 0; i < cars.size(); i++) {
                        System.out.println((i + 1) + ". " + cars.get(i).getColor() + " " + cars.get(i).getBrand() + ", " + cars.get(i).getPower() + " л. с., " + cars.get(i).getNumber() + ", парковка №" + (parkings.indexOf(cars.get(i).getParking()) + 1) + (cars.get(i).getPlace()!= -1 ? "" : (", место " + (cars.get(i).getPlace()+1))));
                    }
                    break;
                case 7:
                    System.out.print("Введите номер машины: ");
                    cars.get(scanner.nextInt() - 1).park();
                    break;
                case 8:
                    System.out.print("Введите номер машины: ");
                    cars.get(scanner.nextInt() - 1).unpark();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Введите число от 1 до 9!");
                    break;
            }
            System.out.println();
        }
    }
}
