package ru.karamoff;

public class DummyBSTree extends BSTree<Integer> {

    BSTree<Integer> getTree() {
        BSTree<Integer> tree = new DummyBSTree();

        tree.root = new Node(5);

        tree.root.left = new Node(2);
        tree.root.right = new Node(7);

        tree.root.left.left = new Node(2);
        tree.root.left.right = new Node(3);
        tree.root.left.right.right = new Node(4);

        tree.root.right.right = new Node(8);
        tree.root.right.right.right = new Node(9);

        tree.levels.add(1);
        tree.levels.add(2);
        tree.levels.add(3);
        tree.levels.add(2);

        return tree;
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
