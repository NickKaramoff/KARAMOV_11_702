package ru.karamoff;

import java.util.Arrays;

public class PathFinder {

    private int[][] field;
    private int length = -1;

    public PathFinder(int[][] field) {
        this.field = field;
    }

    public int find(int startX, int startY, int endX, int endY)
            throws IllegalArgumentException {

        length = -1;
        check(startX, startY, endX, endY);
        mark(startX, startY, 0, endX, endY);

        System.out.println(length >= 0
                ? "Длина пути: " + length + "\n"
                : "Пути нет\n");
        System.out.println(toString());

        return length;
    }

    private void mark(int x, int y, int val, int endX, int endY) {
        field[x][y] = val;

        if (x == endX && y == endY) {
            length = val;
            return;
        }

        if (field[endX][endY] != -1) return;

        val++;

        if (
                (x + 1) == endX && y == endY
                        || (x - 1) == endX && y == endY
                        || x == endX && (y + 1) == endY
                        || x == endX && (y - 1) == endY
                ) {

            mark(endX, endY, val, endX, endY);
            return;

        }

        if ((x + 1) >= 0 && (x + 1) < field.length) {
            if (y >= 0 && y < field[(x + 1)].length) {
                if (field[x + 1][y] == -1 || field[x + 1][y] > val) {
                    mark((x + 1), y, val, endX, endY);
                }
            }
        }

        if ((x - 1) >= 0 && (x - 1) < field.length) {
            if (y >= 0 && y < field[(x - 1)].length) {
                if (field[x - 1][y] == -1 || field[x - 1][y] > val) {
                    mark((x - 1), y, val, endX, endY);
                }
            }
        }

        if (x >= 0 && x < field.length) {
            if ((y + 1) >= 0 && (y + 1) < field[x].length) {
                if (field[x][y + 1] == -1 || field[x][y + 1] > val) {
                    mark(x, (y + 1), val, endX, endY);
                }
            }
        }

        if (x >= 0 && x < field.length) {
            if ((y - 1) >= 0 && (y - 1) < field[x].length) {
                if (field[x][y - 1] == -1 || field[x][y - 1] > val) {
                    mark(x, (y - 1), val, endX, endY);
                }
            }
        }
    }

    private void check(int startX, int startY, int endX, int endY)
            throws IllegalArgumentException {
        if (startX < 0 || startX >= field.length
                || startY < 0 || startY >= field[startX].length) {

            throw new IllegalArgumentException("Начало за границами поля!");

        }

        if (endX < 0 || endX >= field.length
                || endY < 0 || endY >= field[endX].length) {

            throw new IllegalArgumentException("Конец за границами поля!");

        }

        if (field[startX][startY] == -2) {

            throw new IllegalArgumentException("Начало — непроходимая ячейка!");

        }

        if (field[endX][endY] == -2) {

            throw new IllegalArgumentException("Конец — непроходимая ячейка!");

        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] row : field) {
            for (int cell : row) {
                if (cell == -1) {
                    builder.append("\u001B[100m").append("\t").append("\u001B[0m");
                } else if (cell == -2) {
                    builder.append("\u001B[40m").append("\t").append("\u001B[0m");
                } else if (cell == length) {
                    builder.append("\u001B[100m").append("\u001B[93m").append(cell).append("\t").append("\u001B[0m");
                } else if (cell == 0) {
                    builder.append("\u001B[100m").append("\u001B[92m").append(cell).append("\t").append("\u001B[0m");
                } else {
                    builder.append("\u001B[100m").append("\u001B[97m").append(cell).append("\t").append("\u001B[0m");
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
        length = -1;
    }
}
