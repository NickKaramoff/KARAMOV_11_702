package ru.karamoff;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class BSTree<T extends Comparable<T>> implements Tree<T> {

    private class Node {
        Node left, right;
        T value;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }


    private Node root;
    private int height;


    public BSTree() {
        this.root = null;
    }

    public BSTree(T[] elements) {
        for (T elem : elements) {
            insert(elem);
        }
    }


    @Override
    public void insert(T value) {
        root = insert(value, root, 0);
    }

    private Node insert(T value, Node root, int level) {
        if (root == null) {
            root = new Node(value);
            height = height <= level ? level : height;
        } else {
            if (value.compareTo(root.value) <= 0) {
                root.left = insert(value, root.left, level + 1);
            } else {
                root.right = insert(value, root.right, level + 1);
            }
        }

        return root;
    }


    @Override
    public boolean remove(T value) {
        boolean contained = contains(value);
        this.root = remove(value, this.root);

        return contained;
    }

    private Node remove(T value, Node root) {
        if (root.value.compareTo(value) == 0) {
            if (root.left != null && root.right != null) {
                root.value = findMin(root.right).value;
                root.right = remove(root.value, root.right);
            } else {
                if (root.left != null) {
                    return root.left;
                } else if (root.right != null) {
                    return root.right;
                } else {
                    return null;
                }
            }
        } else {
            if (root.value.compareTo(value) > 0) {
                root.left = remove(value, root.left);
            } else {
                root.right = remove(value, root.right);
            }
        }
        return root;
    }

    private Node findMin(Node root) {
        if (root.left == null) {
            return root;
        }

        return findMin(root.left);
    }


    @Override
    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, Node root) {
        if (root != null) {
            if (root.value.compareTo(value) > 0) {
                return contains(value, root.left);
            } else if (root.value.compareTo(value) < 0) {
                return contains(value, root.right);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    @Override
    public void print() {
        print(root);
    }

    private void print(Node root) {
        if (root != null) {
            print(root.left);
            System.out.print(root.value);
            print(root.right);
        }
    }


    @Override
    public void printByLevels() {
        ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
        printByLevels(root, 0, nodes); // генерируем список по уровням

        nodes.remove(nodes.size() - 1); // последний уровень всегда нулевой

        // заполняем оставшееся нуллами, чтобы обеспечить деревообразный вывод
        for (int i = 0; i < nodes.size(); i++) {
            while (nodes.get(i).size() < (1 << i)) {
                nodes.get(i).add(null);
            }
        }

        // выводим корень
        System.out.println(nodes.get(0).get(0).value);

        for (int i = 1; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).size(); j++) {
                Node node = nodes.get(i).get(j);

                // вывод значения или дефиса-пропуска
                System.out.print((node != null ? node.value : "∙"));

                // вывод нужного количества табов: 2^(высота-уровень-1)
                for (int k = 0; k < (1 << (nodes.size() - i - 1)); k++) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private void printByLevels(Node root, int level, ArrayList<ArrayList<Node>> nodes) {
        if (level == nodes.size()) {
            nodes.add(new ArrayList<>());
        }
        nodes.get(level).add(root);

        if (root != null) {
            printByLevels(root.left, level + 1, nodes);
            printByLevels(root.right, level + 1, nodes);
        } else if (level < height) {
            printByLevels(null, level + 1, nodes);
            printByLevels(null, level + 1, nodes);
        }
    }


    @Override
    public boolean isBST() {
        return isBST(root);
    }

    private boolean isBST(Node root) {
        if (root != null) {
            if (
                    (root.left == null || root.left.value.compareTo(root.value) <= 0)
                            &&
                            (root.right == null || root.right.value.compareTo(root.value) > 0)
                    ) {
                return isBST(root.left) && isBST(root.right);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }


    public boolean equals(BSTree<T> other) {
        return equals(this.root, other.root);
    }

    private boolean equals(Node tR, Node oR) {
        if (tR == null && oR == null) {
            return true;
        }

        if (tR == null || oR == null) {
            return false;
        }

        if ( tR.value.equals(oR.value)) {
            return equals(tR.right, oR.right) && equals(tR.left, oR.left);
        } else {
            return false;
        }
    }
}
