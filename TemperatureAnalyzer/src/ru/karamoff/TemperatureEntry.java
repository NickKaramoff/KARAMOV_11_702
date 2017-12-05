package ru.karamoff;

public class TemperatureEntry {
    private int day, month;
    private double temperature;

    public TemperatureEntry(int day, int month, double temperature) {
        this.day = day;
        this.month = month;
        this.temperature = temperature;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public double getTemperature() {
        return temperature;
    }
}
