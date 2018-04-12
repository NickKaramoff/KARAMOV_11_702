package ru.karamoff;

public class Optimizer {
    public static int optimize(int[] nodes, int k) {
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
