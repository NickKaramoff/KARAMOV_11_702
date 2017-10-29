package ru.karamoff;

import java.time.LocalTime;

class Parking {
    private LocalTime openTime, closeTime;
    private Vehicle vehicles[];
    private int fullness = 0;
    private int capacity;

    public Parking() {
        openTime = LocalTime.of(8, 0);
        closeTime =  LocalTime.of(23, 0);
        capacity = 10;
        vehicles = new Vehicle[capacity];
    }

    public Parking(LocalTime openTime, LocalTime closeTime, int capacity) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.capacity = capacity;
        vehicles = new Vehicle[capacity];
    }

    public void vehicleIn(Vehicle vehicle) {
        if (LocalTime.now().isAfter(openTime) && LocalTime.now().isBefore(closeTime)) {
            if(!vehicle.isParked()) {
                if (fullness < vehicles.length) {
                    int i = 0;
                    while (vehicles[i] != null) {
                        i++;
                    }
                    vehicles[i] = vehicle;
                    vehicle.setPlace(i);
                    fullness++;
                } else {
                    System.err.println("На парковке нет места!");
                }
            } else {
                System.err.println("Машина уже припаркована!");
            }
        } else {
            System.err.println("Парковка работает с " + openTime.getHour() + " до " + closeTime.getHour() + "!");
        }
    }

    public void vehicleOut(Vehicle vehicle, int place) {
        if (LocalTime.now().isAfter(openTime) && LocalTime.now().isBefore(closeTime)) {
            if(vehicle.isParked()) {
                vehicles[place] = null;
                vehicle.setPlace(-1);
                fullness--;
            } else {
                System.err.println("Машина не припаркована!");
            }
        } else {
            System.err.println("Парковка работает с " + openTime.getHour() + " до " + closeTime.getHour() + "!");
        }
    }

    public int getFullness() {
        return fullness;
    }

    public int getCapacity() {
        return capacity;
    }
}
