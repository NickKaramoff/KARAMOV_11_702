package ru.karamoff;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String input = "X1:=128;Y2:=56;Z1:=X1+Y2;M5:=X1-Y2*Z1;Y2:=M5/X1;";
        ArrayList<Variable> list = Analyzer.process(input);
        System.out.println(list.toString());
    }
}
