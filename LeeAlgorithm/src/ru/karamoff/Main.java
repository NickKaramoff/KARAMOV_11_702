package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        PathFinder finder = new PathFinder(new int[][]{
                {-1, -1, -1, -1, -1},
                {-1, -2, -2, -1, -1},
                {-2, -2, -1, -1, -1},
                {-1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1}
        });

        finder.find(0, 0, 4, 4);
    }
}
