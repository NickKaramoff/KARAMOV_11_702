package ru.karamoff;

public class DummyBSTree extends BSTree<Integer> {

    BSTree<Integer> getTree() {
        root = new Node(5);

        root.left = new Node(2);
        root.right = new Node(7);

        root.left.left = new Node(2);
        root.left.right = new Node(3);
        root.left.right.right = new Node(4);

        root.right.right = new Node(8);
        root.right.right.right = new Node(9);

        levels.add(1);
        levels.add(2);
        levels.add(3);
        levels.add(2);

        return this;
    }

    BSTree<Integer> getBadTree() {
        BSTree<Integer> tree = new DummyBSTree();

        tree.root = new Node(5);

        tree.root.left = new Node(7);
        tree.root.right = new Node(2);

        tree.levels.add(1);
        tree.levels.add(2);

        return tree;
    }
}
