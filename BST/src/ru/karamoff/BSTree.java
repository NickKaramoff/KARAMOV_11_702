package ru.karamoff;

public class BSTree<T extends Comparable<T>> implements Tree<T> {

    private class Node {
        Node left, right;
        T value;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    Node root;


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
        root = insert(value, root);
    }

    private Node insert(T value, Node root) {
        if (root == null) {
            root = new Node(value);
        } else {
            if (value.compareTo(root.value) <= 0) {
                root.left = insert(value, root.left);
            } else {
                root.right = insert(value, root.right);
            }
        }

        return root;
    }


    @Override
    public boolean remove(T value) {
        return contains(value) && remove(value, this.root);
    }

    private boolean remove(T value, Node root) {
        return false;
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
                    (root.right == null ||root.right.value.compareTo(root.value) > 0)
               ) {
                return isBST(root.left) && isBST(root.right);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
