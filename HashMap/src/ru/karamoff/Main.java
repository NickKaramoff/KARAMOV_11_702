package ru.karamoff;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("hello", 34);
        System.out.println(map.get("hello"));
        System.out.println(map.get("gg"));
    }
}
