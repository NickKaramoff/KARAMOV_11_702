package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        Square square = new Square(2);

        Rectangle rectangle = new Rectangle(2, 3);

        Ellipse ellipse = new Ellipse(5, 8);

        Circle circle = new Circle(6);

        Shape shapes[] = new Shape[]{square, rectangle, ellipse, circle};

        for (Shape shape : shapes) {
            System.out.println(shape.area() + ", " + shape.perimeter());
        }
    }
}
