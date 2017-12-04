package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        IntegerList list = new IntegerList();

        list.add(5);
        list.add(4);
        list.add(29);
        list.add(12);
        list.add(7);
        list.add(48);

        list = list.map(number -> {
            int result = 0;
            while (number != 0) {
                result += number % 10;
                number /= 10;
            }
            return result;
        });

        list = list.filter(number -> number % 2 == 0);

        list.output();
    }
}
