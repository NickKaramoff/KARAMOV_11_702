package ru.karamoff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String line = reader.readLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int k = Integer.parseInt(line.split(" ")[1]);

        int result = 0;

        int[] nodes = new int[n];


        for (int i = 1; i < n; i++) {
            nodes[Integer.parseInt(reader.readLine())]++;
        }

        System.out.println(Arrays.toString(nodes));
        System.out.println(Optimizer.optimize(nodes, k));
    }
}
