package ru.karamoff;

public class RegularMatrix implements Matrix {

    private Number[][] matrix;

    public RegularMatrix(int width, int height) {
        matrix = new Number[width][height];
    }

    @Override
    public void put(int i, int j, Number value) {
        matrix[i][j] = value;
    }

    @Override
    public Number get(int i, int j) {
        return matrix[i][j];
    }
}
