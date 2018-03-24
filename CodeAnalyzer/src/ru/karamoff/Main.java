package ru.karamoff;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String input = "X1:=45;X2:=36;X3:=X1+X2*X1;";
        Analyzer.analyze(input);
        ArrayList<Variable> list = Analyzer.process(input);
        System.out.println(list.toString());
    }
}
