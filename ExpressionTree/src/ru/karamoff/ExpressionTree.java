package ru.karamoff;

public class ExpressionTree {

    static class Node {
        int value;
        boolean isOperation;

        Node left, right;

        public Node(int value, boolean isOperation) {
            this.value = value;
            this.isOperation = isOperation;
        }
    }

    public ExpressionTree(String expression) {

        int numOfParentheses;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if ()
        }
        // TODO make constructor from a given expression
    }

    public static ExpressionTree getExampleInstance() {
        ExpressionTree tree = new ExpressionTree(null);

        tree.root = new Node(2, true);
        tree.root.left = new Node(0, true);
        tree.root.left.left = new Node(2, false);
        tree.root.left.right = new Node(3, false);
        tree.root.right = new Node(1, true);
        tree.root.right.left = new Node(8, false);
        tree.root.right.right = new Node(5, false);

        return tree;
    }

    Node root;

    public int calculate() {
        return calculate(root);
    }

    private int calculate(Node root) {
        if (!root.isOperation) return root.value;
        return performOperation(root.value, calculate(root.left), calculate(root.right));
    }

    private int performOperation(int operation, int a, int b) {
        switch (operation) {
            case 0:
                return a+b;
            case 1:
                return a-b;
            case 2:
                return a*b;
            case 3:
                return a/b;
        }

        return 0;
    }
}
