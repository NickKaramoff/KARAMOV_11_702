package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5, 2, 7, 3, 8, 4, 2, 9};
        BSTree<Integer> tree = new BSTree(a);

        tree.print();

        System.out.println();
        tree.remove(4);

        tree.print();
    }
}
