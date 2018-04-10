package ru.karamoff.test;

import org.junit.*;
import ru.karamoff.Analyzer;
import ru.karamoff.SyntaxException;

public class AnalyzeTest {
    
    @Test
    public void correctData() {
        String input = "X1:=128;Y2:=56;Z1:=X1+Y2;M5:=X1+Y2+Z1;";
        Analyzer.analyze(input);
    }

    @Test (expected = SyntaxException.class)
    public void unfinishedString() {
        String input = "X1:=128;Y2:=56;Z1:=X1+Y2;M5:=X1+Y2+Z1";
        Analyzer.analyze(input);
    }

    @Test (expected = SyntaxException.class)
    public void noLetter() {
        String input = "X1:=128;Y2:=56;51:=X1+Y2;M5:=X1+Y2+Z1;";
        Analyzer.analyze(input);
    }

    @Test (expected = SyntaxException.class)
    public void noDigit() {
        String input = "X1:=128;Y2:=56;ZA:=X1+Y2;M5:=X1+Y2+Z1;";
        Analyzer.analyze(input);
    }

    @Test (expected = SyntaxException.class)
    public void noColon() {
        String input = "X1:=128;Y2=56;Z1:=X1+Y2;M5:=X1+Y2+Z1;";
        Analyzer.analyze(input);
    }

    @Test (expected = SyntaxException.class)
    public void noEquals() {
        String input = "X1:=128;Y2:56;Z1:=X1+Y2;M5:=X1+Y2+Z1;";
        Analyzer.analyze(input);
    }

    @Test (expected = SyntaxException.class)
    public void noOperation() {
        String input = "X1:=128;Y2:=56;Z1:=X1Y2;M5:=X1+Y2+Z1;";
        Analyzer.analyze(input);
    }

    @Test (expected = SyntaxException.class)
    public void noSemicolon() {
        String input = "X1:=128;Y2:=56Z1:=X1+Y2;M5:=X1+Y2+Z1;";
        Analyzer.analyze(input);
    }
}
