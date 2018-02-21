package ru.karamoff;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Point> p = new ArrayList<>();

        p.add(new Point(0, 6));
        p.add(new Point(1, 9));
        p.add(new Point(5, 13));
        p.add(new Point(14, 12));
        p.add(new Point(18, 7));
        p.add(new Point(16, 3));
        p.add(new Point(13, 0));
        p.add(new Point(8, 1));
        p.add(new Point(3, 2));

        p.add(new Point(4, 10));
        p.add(new Point(4, 6));
        p.add(new Point(8, 5));
        p.add(new Point(11, 2));
        p.add(new Point(12, 5));
        p.add(new Point(13, 8));
        p.add(new Point(7, 9));
        p.add(new Point(10, 9));
        p.add(new Point(11, 11));

        Hull h = new Hull(p);

        System.out.println(h.toString());
    }
}
