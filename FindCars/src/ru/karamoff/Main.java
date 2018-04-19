package ru.karamoff;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            File userFile = new File("users.txt");
            File carFile = new File("cars.txt");

            BufferedReader userReader = new BufferedReader(new FileReader(userFile));
            BufferedReader carReader = new BufferedReader(new FileReader(carFile));

            String user = userReader.readLine();
            String car = carReader.readLine();

            HashMap<Integer, Integer> ages = new HashMap<>();

            int userId = -1;
            int age = -1;

            while (user != null && car != null) {

                userId = Integer.parseInt(user.split(" ")[0]);
                int carOwnerId = Integer.parseInt(car.split(" ")[2]);

                if (userId > carOwnerId) {
                    car = carReader.readLine();
                } else if (userId < carOwnerId) {
                    user = userReader.readLine();
                } else {
                    age = Integer.parseInt(user.split(" ")[2]);

                    if (ages.containsKey(age)) {
                        ages.put(age, ages.get(age) + 1);

                    } else {
                        ages.put(age, 1);
                    }

                    car = carReader.readLine();
                }
            }

            while (car != null) {
                if (Integer.parseInt(car.split(" ")[2]) == userId) {
                    ages.put(age, ages.get(age) + 1);
                }

                car = carReader.readLine();

            }

//            SortedSet<Integer> keys = new TreeSet<>(ages.keySet());

            for (Integer key : new TreeSet<>(ages.keySet())) {
                System.out.println(key + "\t" + ages.get(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
