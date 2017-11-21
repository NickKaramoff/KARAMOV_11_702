package ru.karamoff;

import java.io.FileInputStream;

public class MyScanner {
    private byte bytes[] = new byte[200];

    public MyScanner(String name) {
        try {
            FileInputStream inputStream = new FileInputStream(name);
            int length = inputStream.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String nextLine() {
        int end = 0;
        while (bytes[end] != 10 && bytes[end] != 13) {
            end++;
        }
        String output = "";
        for (int i = 0; i < end; i++) {
            output += (char) bytes[i];
        }
        return output;
    }

    public int nextInt() {
        int start = 0;
        while (bytes[start] < 48 || bytes[start] > 57) {
            start++;
        }
        int end = start;
        while (bytes[end] >= 48 && bytes[end] <= 57) {
            end++;
        }

        int output = 0;
        for (int i = start; i < end; i++) {
            output *= 10;
            output += bytes[i] - 48;
        }
        return output;
    }
}
