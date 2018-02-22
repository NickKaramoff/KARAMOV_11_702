package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        list1.add(8);
        list1.add(3);
        list1.add(5);
        list1.add(9);
        list1.add(6);

        list2.add(7);
        list2.add(2);
        list2.add(4);
        list2.add(1);

        System.out.println("List 1: " + list1.toString());
        System.out.println("List 2: " + list2.toString());
        System.out.println();

        list1 = LinkedList.sort(list1);
        list2 = LinkedList.sort(list2);

        System.out.println("List 1 sorted: " + list1.toString());
        System.out.println("List 2 sorted: " + list2.toString());
        System.out.println();

        LinkedList list3 = LinkedList.merge(list1,list2);

        System.out.println("Lists 1 & 2 merged: " + list3);
    }
}