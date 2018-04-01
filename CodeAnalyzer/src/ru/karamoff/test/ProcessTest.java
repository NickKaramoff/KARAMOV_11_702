package ru.karamoff.test;

import org.junit.Assert;
import org.junit.Test;
import ru.karamoff.Analyzer;
import ru.karamoff.Variable;
import ru.karamoff.VariableNotFoundException;

import java.util.ArrayList;

public class ProcessTest {

    @Test
    public void correctData(){
        String input = "X1:=128;Y2:=56;Z1:=X1+Y2;M5:=X1-Y2*Z1;Y2:=M5/X1;";
        ArrayList<Variable> actual = Analyzer.process(input);

        ArrayList<Variable> expected = new ArrayList<>();
        expected.add(new Variable("X1", 128));
        expected.add(new Variable("Y2", 103));
        expected.add(new Variable("Z1", 184));
        expected.add(new Variable("M5", 13248));

        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size();i++){
            Assert.assertTrue(expected.get(i).equalsBoth(actual.get(i)));
        }
    }

    @Test (expected = VariableNotFoundException.class)
    public void wrongData() {
        String input = "X1:=128;Y2:=Z1;";
        ArrayList<Variable> actual = Analyzer.process(input);
    }
}
