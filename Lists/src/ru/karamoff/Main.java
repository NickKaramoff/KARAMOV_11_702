package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        List list = new LinkedList();
        list.addToBegin(123); // 123
        list.addToBegin(124); // 124, 123
        list.addToBegin(125); // 125, 124, 123
        list.addToBegin(126); // 126, 125, 124, 123
        list.add(505);              // 126, 125, 124, 123, 505
        list.add(404);              // 126, 125, 124, 123, 505, 404

        System.out.println(list.toString());

        list.remove(123);   // 126, 125, 124, 505, 404
        list.remove(126);   // 125, 124, 505, 404
        list.remove(404);   // 125, 124, 505

        System.out.println(list.toString());
    }
}