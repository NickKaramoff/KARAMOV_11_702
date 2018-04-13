package ru.karamoff.test;

import org.junit.*;
import ru.karamoff.Analyzer;
import ru.karamoff.Variable;
import ru.karamoff.VariableNotFoundException;

import java.util.ArrayList;


public class ProcessTest {
    @Test (expected = VariableNotFoundException.class)
    public void wrongCode() {
        String input = "X1:=10;T5:=Z7;";
        ArrayList<Variable> res = Analyzer.process(input);
    }

    @Test
    public void rightCode() {
        String input = "X1:=5;Y2:=10;T1:=X1;N9:=X1+Y2;M0:=N9/X1+N9;";
        ArrayList<Variable> res = Analyzer.process(input);
        ArrayList<Variable> exp = new ArrayList<>();

        exp.add(new Variable("X1", 5));
        exp.add(new Variable("Y2", 10));
        exp.add(new Variable("T1", 5));
        exp.add(new Variable("N9", 15));
        exp.add(new Variable("M0", 18));

        Assert.assertEquals(exp.size(), res.size());

        for (int i = 0; i < res.size(); i++) {
            Assert.assertTrue(exp.get(i).equalsBoth(res.get(i)));
        }
    }
}

