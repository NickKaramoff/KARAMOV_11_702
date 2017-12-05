package ru.karamoff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    private final static int MONTHS = 12;

    public static void main(String[] args) {
        ArrayList<TemperatureEntry> entries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String s = reader.readLine();

            while (s != null) {
                String[] splitString = s.split(" ");
                String[] dateSplitString = splitString[0].split("\\.");

                int day = Integer.parseInt(dateSplitString[0]);
                int month = Integer.parseInt(dateSplitString[1]);
                double temperature = Double.parseDouble(splitString[1]);
                entries.add(new TemperatureEntry(day, month, temperature));

                s = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double[] months = new double[MONTHS];
        int[] amountOfDays = new int[MONTHS];

        for (TemperatureEntry entry : entries) {
            months[entry.getMonth() - 1] += entry.getTemperature();
            amountOfDays[entry.getMonth() - 1]++;
        }

        double yearAvg = 0;

        for (int i = 0; i < MONTHS; i++) {
            months[i] /= amountOfDays[i];
            yearAvg += months[i];
        }

        yearAvg /= MONTHS;

        double minimum = Double.MAX_VALUE;

        for (double month : months) {
            if (Math.abs(month - yearAvg) < minimum) {
                minimum = Math.abs(month - yearAvg);
            }
        }

        for (int i = 0; i < MONTHS; i++) {
            if (Math.abs(months[i] - yearAvg) == minimum) {
                System.out.println(Month.values()[i]);
            }
        }

    }
}
