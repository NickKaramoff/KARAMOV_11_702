package ru.karamoff;

import java.time.LocalTime;

/**
 * Класс, описывающий работу парковки с несколькими местами для автомобилей
 * @author Никита Карамов
 * @version 1.1
 * @see Car Класс, описывающий автомобиль
 */


class Parking {

    /** Время открытия парковки */
    private LocalTime openTime;
    /** Время закрытия парковки */
    private LocalTime closeTime;
    /** Автомобили на парковке */
    private Car cars[];
    /** Заполненность парковки */
    private int fullness = 0;
    /** Вместимость парковки */
    private int capacity;

    /**
     * Инициализатор парковки. По умолчанию парковка содержит место на 10 автомобилей и работает с 8 до 23.
     */
    public Parking() {
        openTime = LocalTime.of(8, 0);
        closeTime =  LocalTime.of(23, 0);
        capacity = 10;
        cars = new Car[capacity];
    }

    /**
     * Инициализатор парковки с параметрами
     * @param openTime Время открытия
     * @param closeTime Время закрытия
     * @param capacity Вместимость
     */
    public Parking(LocalTime openTime, LocalTime closeTime, int capacity) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.capacity = capacity;
        cars = new Car[capacity];
    }

    /**
     * Функция постановки автомобиля на парковку. Запускается методом {@link Car#park()}
     * @param car Автомобиль, что ставят на парковку
     */
    public void takeCar(Car car) {
        if (LocalTime.now().isAfter(openTime) && LocalTime.now().isBefore(closeTime)) {
            if (fullness < cars.length) {
                int i = 0;
                while (cars[i] != null) {
                    i++;
                }
                cars[i] = car;
                car.setPlace(i);
                fullness++;
            } else {
                System.err.println("На парковке нет места!");
            }

        } else {
            System.err.println("Парковка работает с " + openTime.getHour() + " до " + closeTime.getHour() + "!");
        }
    }

    /**
     * Функция снятия автомобиля с парковки. Запускается методом {@link Car#unpark()}
     * @param car Автомобиль, снимаемый с парковки
     * @param place Место автомобиля на парковке
     */
    public void releaseCar(Car car, int place) {
        if (LocalTime.now().isAfter(openTime) && LocalTime.now().isBefore(closeTime)) {
            cars[place] = null;
            car.setPlace(-1);
            fullness--;
        } else {
            System.err.println("Парковка работает с " + openTime.getHour() + " до " + closeTime.getHour() + "!");
        }
    }

    /**
     * @return Количество машин, поставленных на парковку
     */
    public int getFullness() {
        return fullness;
    }


    /**
     * @return Общая вместимость парковки
     */
    public int getCapacity() {
        return capacity;
    }
}
