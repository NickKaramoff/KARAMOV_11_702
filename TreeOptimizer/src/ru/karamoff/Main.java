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

        int[] nodes = new int[n];

        for (int i = 1; i < nodes.length; i++) {
            nodes[Integer.parseInt(reader.readLine())]++;
        }

        System.out.println(Arrays.toString(nodes));
        System.out.println(optimize(nodes, k));
    }

    static int optimize(int[] nodes, int k) {
        if (k <= 1) {
            for (int i : nodes) {
                if (i > k)
                    throw new IllegalArgumentException("Невозможно оптимизировать дерево");
            }
        }

        int result = 0;

        for (int i = 0; i < nodes.length; i++) {
            while (nodes[i] > k) {
                result += nodes[i] / k;
                nodes[i] -= (nodes[i] / k) * (k - 1);
            }
        }


        return result;
    }
}
