package ru.karamoff.logparser.utils;

/**
 * Класс для красивого логирования процесса в консоли. По факту просто красит
 * надписи разными цветами и, при надобности, выделяет жирным.
 *
 * @see ANSI
 */
public class Logger {
    /**
     * Вывод обычного сообщения
     *
     * @param message сообщение
     */
    public static void action(String message) {
        System.out.println(message);
    }

    /**
     * Вывод сообщения об ошибке
     *
     * @param message сообщение
     */
    public static void error(String message) {
        System.out.println(ANSI.BOLD + ANSI.RED + message + ANSI.RESET);
    }

    /**
     * Вывод сообщения об ошибке и сразу же — обычного сообщения
     * @param message сообщение
     * @param next следующее сообщение
     */
    public static void error(String message, String next) {
        System.out.println(ANSI.BOLD + ANSI.RED + message + " " + ANSI.RESET + next);
    }

    /**
     * Вывод предупреждающего сообщения
     *
     * @param message сообщение
     */
    public static void warning(String message) {
        System.out.println(ANSI.YELLOW + message + ANSI.RESET);
    }

    /**
     * Вывод предупреждающего сообщения и сразу же — обычного сообщения
     * @param message сообщение
     * @param next следующее сообщение
     */
    public static void warning(String message, String next) {
        System.out.println(ANSI.YELLOW + message + " " + ANSI.RESET + next);
    }

    /**
     * Вывод сообщения об успехе
     *
     * @param message сообщение
     */
    public static void success(String message) {
        System.out.println(ANSI.GREEN + message + ANSI.RESET);
    }

    /**
     * Вывод сообщения об успехе и сразу же — обычного сообщения
     * @param message сообщение
     * @param next следующее сообщение
     */
    public static void success(String message, String next) {
        System.out.println(ANSI.GREEN + message + " " + ANSI.RESET + next);
    }
}
