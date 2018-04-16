package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5, 2, 7, 3, 8, 4, 2, 9};
        BSTree<Integer> tree = new BSTree(a);

        tree.printByLevels();
        System.out.println();
        System.out.println(tree.height());

        System.out.println();
        tree.remove(4);

        tree.printByLevels();
        System.out.println();
        System.out.println(tree.height());

    }
}
