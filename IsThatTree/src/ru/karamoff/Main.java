package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {0,1,0},
            {1,0,0},
            {0,0,0}
        };

        Graph graph = new Graph(matrix);
        System.out.println(graph.toString());
        System.out.println(graph.isTree());
        System.out.println(graph.isForest());
    }
}
