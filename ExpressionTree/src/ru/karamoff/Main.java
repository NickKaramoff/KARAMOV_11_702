package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        ExpressionTree tree = ExpressionTree.getExampleInstance();

        System.out.println(tree.calculate());
    }
}
