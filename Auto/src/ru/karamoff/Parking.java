package ru.karamoff;

import java.time.LocalTime;
import java.util.ArrayList;

class Parking {
    private LocalTime openTime = LocalTime.of(8, 0);
    private LocalTime closeTime = LocalTime.of(23, 0);

    private ArrayList<Car> cars = new ArrayList<>();

    void takeCar(Car car) {
        if (LocalTime.now().isAfter(openTime) && LocalTime.now().isBefore(closeTime)) {
            cars.add(car);
        } else {
            System.err.println("Парковка работает с 8 до 23!");
        }
    }

    void releaseCar(Car car) {
        if (LocalTime.now().isAfter(openTime) && LocalTime.now().isBefore(closeTime)) {
            cars.remove(car);
        } else {
            System.err.println("Парковка работает с 8 до 23!");
        }
    }

    void listCars() {
        System.out.println("Припаркованы:");
        for (Car car : cars) {
            System.out.println(car.getNumber() + " | " + car.getColor() + " " + car.getBrand());
        }
    }

    int getFullness() {
        return cars.size();
    }
}
