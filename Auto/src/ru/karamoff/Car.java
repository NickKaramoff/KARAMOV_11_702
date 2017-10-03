package ru.karamoff;

/**
 * Класс, описывающий автомобиль
 * @author Никита Карамов
 * @version 1.1
 * @see Parking Класс, описывающий работу парковки
 */

public class Car {
    private String brand, color, number;
    private int power; // мощность в л.с.
    private int mileage = 0; // пробег в километрах
    /** Привязанная парковка */
    private Parking parking;
    /** Место на привязанной парковке. Присваивается методами {@link Parking#takeCar(Car)} и {@link Parking#releaseCar(Car, int)} */
    private int place = -1;

    /**
     * Инициализирует автомобиль с базовыми параметрами
     * @param brand Модель автомобиля
     * @param power Мощность автомобиля (в л. с.)
     */
    public Car(String brand, int power) {
        this.brand = brand;
        this.power = power;
    }

    /**
     * Инициализирует автомобиль с полным набором параметров
     * @param brand Модель автомобиля
     * @param number Рег. номер автомобиля
     * @param power Мощность автомобиля (в л. с.)
     * @param color Цвет автомобиля
     * @param parking Парковка, привязанная к автомобилю
     */
    public Car(String brand, String number, int power, String color, Parking parking) {
        this.brand = brand;
        this.number = number;
        this.power = power;
        this.color = color;
        this.parking = parking;
    }


    /**
     * Метод, увеличивающий пробег автомобиля
     * @param distance Дистанция, пройденная автомобилем
     */
    public void drive(int distance) {
        if (distance >= 0) {
            mileage += distance;
        } else {
            System.err.println("Неверное значение дистанции.");
        }
    }

    /**
     * Метод, ставящий автомобиль на парковку. Запускает {@link Parking#takeCar(Car)}
     */
    public void park() {
        parking.takeCar(this);
    }

    /**
     * Метод, убирающий автомобиль с парковки. Запускает {@link Parking#releaseCar(Car, int)}
     */
    public void unpark() {
        parking.releaseCar(this, place);
    }


    /**
     * @return Модель автомобиля
     */
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    /**
     * @return Цвет автомобиля
     */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    /**
     * @return Рег. номер автомобиля
     */
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * @return Мощность автомобиля
     */
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power > 0) {
            this.power = power;
        } else {
            System.err.println("Неправильное значение");
            System.err.println("Установлено значение по умолчанию");
            this.power = 0;

        }
    }


    /**
     * @return Пробег автомобиля
     */
    public int getMileage() {
        return mileage;
    }


    /**
     * @return Привязанная к автомобилю парковка
     */
    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    /**
     * @return Место автомобиля на парковке
     */
    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
