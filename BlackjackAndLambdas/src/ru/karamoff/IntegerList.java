package ru.karamoff;

public class IntegerList {
    private static final int MAX_NUMBERS_COUNT = 10;
    private int numbers[];
    private int count;

    public IntegerList() {
        this.count = 0;
        this.numbers = new int[MAX_NUMBERS_COUNT];
    }

    void add(int number) {
        if (count <= MAX_NUMBERS_COUNT) {
            numbers[count++] = number;
        } else {
            System.err.println("lol fuk u");
        }
    }

    void output() {
        for (int i = 0; i < count; i++) {
            System.out.println(numbers[i]);
        }
    }

    IntegerList map(Function function) {
        IntegerList newList = new IntegerList();
        for (int i = 0; i < count; i++) {
            newList.add(function.transform(numbers[i]));
        }
        return newList;
    }

    IntegerList filter(Predicate predicate) {
        IntegerList newList = new IntegerList();
        for (int i = 0; i < count; i++) {
            if (predicate.check(numbers[i])) {
                newList.add(numbers[i]);
            }
        }
        return newList;
    }
}
