package ru.karamoff;

import java.util.ArrayList;

public class Analyzer {

    public static void analyze(String input) throws SyntaxException {
        int state = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (state) {
                case 0:
                    if (c >= 'A' && c <= 'Z') {
                        state++;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалась буква A-Z");
                    }
                    break;
                case 1:
                    if (c >= '0' && c <= '9') {
                        state++;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалась цифра 0-9");
                    }
                    break;
                case 2:
                    if (c == ':') {
                        state++;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалось ':'");
                    }
                    break;
                case 3:
                    if (c == '=') {
                        state++;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалось '='");
                    }
                    break;
                case 4:
                    if (c >= '0' && c <= '9') {
                        state = 5;
                    } else if (c >= 'A' && c <= 'Z') {
                        state = 7;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалась буква A-Z или цифра 0-9");
                    }
                    break;
                case 5:
                    if (c == ';') {
                        state = 0;
                    } else if (!(c >= '0' && c <= '9')) {
                        throw new SyntaxException("На позиции " + i + " ожидалось ';' или цифра 0-9");
                    }
                    break;
                case 7:
                    if (c >= '0' && c <= '9') {
                        state = 8;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалась цифра 0-9");
                    }
                    break;
                case 8:
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        state = 9;
                    } else if (c == ';') {
                        state = 0;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалось ';' или знак арифметической операции");
                    }
                    break;
                case 9:
                    if (c >= 'A' && c <= 'Z') {
                        state = 7;
                    } else {
                        throw new SyntaxException("На позиции " + i + " ожидалась буква A-Z");
                    }
                    break;
            }
        }

        if (state != 0) {
            throw new SyntaxException("На позиции " + (input.length() - 1) + " ожидалось ';'");
        }
    }

    public static ArrayList<Variable> process(String input)
            throws SyntaxException, VariableNotFoundException {

        analyze(input);

        ArrayList<Variable> result = new ArrayList<>();
        String[] commands = input.split(";");

        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(":=");
            Variable var = new Variable(command[0], 0);

            if (command[1].matches("\\d+")) {
                var.setValue(Integer.parseInt(command[1]));
            } else if (command[1].contains("+") || command[1].contains("-") || command[1].contains("*") || command[1].contains("/")) {

                int operation = 0; //0-3 <=> +-*/
                int buffer; // то, что потом будет прибавляться/убавляться/ещё что-то

                for (int j = 0; j < command[1].length(); j++) {
                    char c = command[1].charAt(j);
                    buffer = 0;
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        operation = getOperation(c);
                    } else {
                        if (Character.isLetter(c)) {
                            buffer = getVarValue(command[1].substring(j, j + 2), result);
                            j++;
                        } else {
                            while (Character.isDigit(c)) {
                                buffer *= 10;
                                buffer += c - '0';
                                c = command[1].charAt(++j);
                            }
                            j--; // сдвигаем назад, чтобы не пропустить знак операции
                        }

                        int number = var.getValue();

                        switch (operation) {
                            case 0:
                                number += buffer;
                                break;
                            case 1:
                                number -= buffer;
                                break;
                            case 2:
                                number *= buffer;
                                break;
                            case 3:
                                number /= buffer;
                                break;
                        }

                        var.setValue(number);
                    }
                }

            } else {
                var.setValue(getVarValue(command[1], result));
            }

            if (!result.contains(var)) {
                result.add(var);
            } else {
                result.get(result.indexOf(var)).setValue(var.getValue());
            }
        }

        return result;
    }

    private static int getVarValue(String id, ArrayList<Variable> vars) throws VariableNotFoundException {
        Variable otherVar = new Variable(id, 0);
        int otherIndex = vars.indexOf(otherVar);

        if (otherIndex >= 0) {
            return vars.get(otherIndex).getValue();
        } else {
            throw new VariableNotFoundException("Переменная не найдена");
        }
    }

    private static int getOperation(char c) {
        if (c == '+') return 0;
        if (c == '-') return 1;
        if (c == '*') return 2;
        if (c == '/') return 3;
        return 0;
    }
}
