package ru.karamoff;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Arc> arcs = new LinkedList<>();

        arcs.add(new Arc(3, 6));
        arcs.add(new Arc(1, 4));
        arcs.add(new Arc(1, 2));
        arcs.add(new Arc(6, 4));
        arcs.add(new Arc(6, 5));

        System.out.println(Arrays.toString(convert(arcs)));
    }

    /*
        Сложность: O(2m+3n) = O(n)
     */
    public static int[] convert(LinkedList<Arc> arcs) {
        // Нахождение максимального номера вершины => количества вершин
        int maxNumber = 0;
        for (Arc arc : arcs) {
            if (arc.getStart() > maxNumber) maxNumber = arc.getStart();
            if (arc.getEnd() > maxNumber) maxNumber = arc.getEnd();
        }

        // Создание массива размера n+m
        int[] result = new int[maxNumber + 1 + arcs.size()];

        // подсчёт степеней выхода вершин
        for (Arc arc : arcs) {
            result[arc.getStart()]++;
        }

        // ввод индексов начала для каждой вершины
        int rightmost = maxNumber + 1;
        for (int i = 0; i < maxNumber + 1; i++) {
            int temp = result[i];
            result[i] = rightmost;
            rightmost += temp;
        }

        // обнуление индексов, если степень выхода нулевая
        for (int i = 0; i < maxNumber; i++) {
            if (result[i] == result[i + 1]) {
                result[i] = -1;
            }
        }

        // заполнение индексов рёбер номерами конечных вершин
        for (Arc arc : arcs) {
            int start = result[arc.getStart()];
            while (result[start] != 0) {
                start++;
            }
            result[start] = arc.getEnd();
        }

        return result;
    }
}
