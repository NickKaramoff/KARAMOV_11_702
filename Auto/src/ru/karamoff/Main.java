package ru.karamoff;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();
//        Parking parking = new Parking();
        ArrayList<Parking> parkings = new ArrayList<>();


        System.out.println("Парковочный робот 2.0\nТеперь с поддержкой тракторов \"Беларусь\"!\n");

        while (true) {
            System.out.println("1. Добавить парковку");
            System.out.println("2. Удалить парковку");
            System.out.println("3. Информация о парковках");
            System.out.println("4. Добавить транспортное средство");
            System.out.println("5. Удалить транспортное средство");
            System.out.println("6. Отобразить все транспортные средства");
            System.out.println("7. Припарковать транспортное средство");
            System.out.println("8. Снять транспортное средство с парковки");
            System.out.println("9. Переприсвоить парковку транспортному средству");
            System.out.println("0. Выход");
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
                    vehicles.remove(scanner.nextInt() - 1);
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
                    System.out.print("Введите мощность: ");
                    int power = scanner.nextInt();
                    System.out.print("Введите номер парковки: ");
                    Parking parking = parkings.get(scanner.nextInt() - 1);
                    System.out.println("1. Машина");
                    System.out.println("2. Спорткар");
                    System.out.println("3. Самолёт");
                    System.out.println("4. Танк");
                    System.out.println("5. Трактор \"Беларусь\"");
                    System.out.print("Выберите тип транспортного средства: ");
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.print("Есть запасное колесо? (y/n): ");
                            vehicles.add(new Car(brand, power, parking, scanner.next().equals("y")));
                            break;
                        case 2:
                            boolean spareWheel;
                            spareWheel = scanner.next().equals("y");
                            System.out.print("Введите расход топлива на 100 км: ");
                            vehicles.add(new SportCar(brand, power, parking, spareWheel, scanner.nextDouble()));
                            break;
                        case 3:
                            System.out.print("Введите количество двигателей: ");
                            vehicles.add(new Airplane(brand, power, parking, scanner.nextInt()));
                            break;
                        case 4:
                            System.out.print("Введите длину пушки (м): ");
                            vehicles.add(new Tank(brand, power, parking, scanner.nextDouble()));
                            break;
                        case 5:
                            System.out.print("Введите количество картошки (кг): ");
                            vehicles.add(new TractorBelarus(brand, power, parking, scanner.nextDouble()));
                            break;
                        default:
                            System.out.println("Введите число от 1 до 5!");
                            break;
                    }
                    break;
                case 5:
                    System.out.print("Введите номер транспортного средства: ");
                    vehicles.remove(scanner.nextInt() - 1);
                    break;
                case 6:
                    for (int i = 0; i < vehicles.size(); i++) {
                        System.out.println((i + 1) + ". " + vehicles.get(i).getClass().getSimpleName() + " " + vehicles.get(i).getBrand() + ", " + vehicles.get(i).getPower() + " л. с." + ", парковка №" + (parkings.indexOf(vehicles.get(i).getParking()) + 1) + (vehicles.get(i).isParked() ? (", место " + (vehicles.get(i).getPlace() + 1)) : ""));
                    }
                    break;
                case 7:
                    System.out.print("Введите номер транспортного средства: ");
                    vehicles.get(scanner.nextInt() - 1).park();
                    break;
                case 8:
                    System.out.print("Введите номер транспортного средства: ");
                    vehicles.get(scanner.nextInt() - 1).unpark();
                    break;
                case 9:
                    System.out.print("Введите номер транспортного средства: ");
                    int carNumber = scanner.nextInt();
                    if (vehicles.get(carNumber).isParked()) {
                        vehicles.get(carNumber).unpark();
                    }
                    System.out.print("Введите номер новой парковки: ");
                    vehicles.get(carNumber).setParking(parkings.get(scanner.nextInt()));
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Введите однозначное число!");
                    break;
            }
            System.out.println();
        }
    }
}
