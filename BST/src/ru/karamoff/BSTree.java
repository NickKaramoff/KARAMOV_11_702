package ru.karamoff;

import java.util.ArrayList;

public class BSTree<T extends Comparable<T>> implements Tree<T> {

    class Node {
        Node left, right;
        T value;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    Node root;
    ArrayList<Integer> levels = new ArrayList<>();


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

            if (level == levels.size()) {
                levels.add(1);
            } else {
                levels.set(level, levels.get(level) + 1);
            }
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
        if (!contains(value)) return false;

        root = remove(value, root, 0);
        return true;
    }

    private Node remove(T value, Node root, int level) {
        if (root.value.compareTo(value) == 0) {
            if (root.left != null && root.right != null) {
                root.value = findMax(root.left).value;
                root.left = remove(root.value, root.left, level + 1);
            } else {

                levels.set(level, levels.get(level) - 1);
                if (levels.get(levels.size() - 1) == 0) {
                    levels.remove(levels.size() - 1);
                }

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
                root.left = remove(value, root.left, level + 1);
            } else {
                root.right = remove(value, root.right, level + 1);
            }
        }
        return root;
    }

    private Node findMax(Node root) {
        if (root.right == null) {
            return root;
        }

        return findMax(root.right);
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
            System.out.print(root.value + " ");
            print(root.right);
        }
    }


    @Override
    public void printByLevels() {
        T[] nodeArr = (T[]) new Comparable[(1 << (height() + 1)) - 1];

        distributeLevels(root, 0, nodeArr);

        // выводим корень
        System.out.println(nodeArr[0]);

        for (int i = 1; i < height(); i++) {
            for (int j = (1 << i) - 1; j < (1 << (i + 1)) - 1; j++) {
                System.out.print((nodeArr[j] != null ? nodeArr[j] : "∙"));
                for (int k = 0; k < (1 << (height() - i - 1)); k++) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private void distributeLevels(Node root, int position, T[] array) {
        if (root != null) {
            array[position] = root.value;
            distributeLevels(root.left, (2 * position) + 1, array);
            distributeLevels(root.right, (2 * position) + 2, array);
        } else {
            array[position] = null;
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

        if (tR.value.equals(oR.value)) {
            return equals(tR.right, oR.right) && equals(tR.left, oR.left);
        } else {
            return false;
        }
    }

    public int height() {
        return levels.size();
    }
}
