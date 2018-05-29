package ru.karamoff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graph {

    private Vertex[] vertices;

    public Graph(int[][] matrix) {
        vertices = new Vertex[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    vertices[i].addNeighbour(vertices[j]);
                    vertices[j].addNeighbour(vertices[i]);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            Collections.sort(vertices[i].neighbors);
        }
    }

    public boolean isTree() {
        if (vertices.length == 0) return true;

        vertices[0].mark();
        boolean mark = vertices[0].markNeighbors(null);
        if (!mark) return false;
        for (Vertex v : vertices) {
            if (!v.marked) return false;
        }
        return true;
    }

    public boolean isForest() {
        if (isTree()) return true;

        boolean mark = true;
        for (Vertex v : vertices) {
            if (!v.marked) {
                v.mark();
                mark = mark & v.markNeighbors(null);
            }
        }
        return mark;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Vertex v : vertices) {
            builder.append(v.toString()).append("\n");
        }
        return builder.toString();
    }

    private class Vertex implements Comparable<Vertex> {
        private ArrayList<Vertex> neighbors;
        private int number;
        private boolean marked;

        public Vertex(int number) {
            this(number, new ArrayList<>());
        }

        public Vertex(int number, ArrayList<Vertex> neighbors) {
            this.number = number;
            this.neighbors = neighbors;
            marked = false;
        }

        private void addNeighbour(Vertex neighbor) {
            neighbors.add(neighbor);
        }

        private boolean markNeighbors(Vertex origin) {
            boolean result = true;
            for (Vertex v : neighbors) {
                if (origin == null || v.number != origin.number) {
                    if (!v.marked) {
                        v.mark();
                        result = result & v.markNeighbors(this);
                    } else {
                        return false;
                    }
                }
            }

            return result;
        }

        public void mark() {
            this.marked = true;
        }

        @Override
        public String toString() {
            return "Vertex #" + this.number
                    + ". Neighbors: " + Arrays.toString(neighbors.stream()
                                                                 .map(v -> v.number)
                                                                 .toArray());
        }

        @Override
        public int compareTo(Vertex other) {
            return this.number - other.number;
        }
    }
}
