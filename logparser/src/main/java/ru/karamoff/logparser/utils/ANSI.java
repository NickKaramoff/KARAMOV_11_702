package ru.karamoff.logparser.utils;

/**
 * Утилитарный класс, содержащий константные escape-строки ANSI для
 * форматирования цвета в консоли терминала.
 */
public class ANSI {
    //  Сброс форматирования
    public static final String RESET = "\u001B[0m";

    //  Цвета (используются Bright, кроме чёрного)
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[91m";
    public static final String GREEN = "\u001B[92m";
    public static final String YELLOW = "\u001B[93m";
    public static final String BLUE = "\u001B[94m";
    public static final String PURPLE = "\u001B[95m";
    public static final String CYAN = "\u001B[96m";
    public static final String WHITE = "\u001B[97m";

    //  Стилистика
    public static final String BOLD = "\u001B[1m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
}
