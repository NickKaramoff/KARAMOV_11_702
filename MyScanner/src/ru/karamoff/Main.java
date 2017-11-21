package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner("input.txt");
        System.out.println(myScanner.nextInt());
        System.out.println(myScanner.nextLine());
    }
}
