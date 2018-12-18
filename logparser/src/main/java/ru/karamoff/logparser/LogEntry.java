package ru.karamoff.logparser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.karamoff.logparser.utils.ANSI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, представляющий собой одну запись лога.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {
    private String ip;
    private LocalDateTime dateTime;
    private String method;
    private String url;
    private String protocol;
    private int statusCode;
    private int size;
    private int clientId;


    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MMM/yyyy:HH:mm:ss xxx", Locale.US);
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    /**
     * Утилитарный метод для {@code toString()}. Красит заголовок поля и
     * выравнивает его пробелами
     *
     * @param s строка с названием
     * @return форматированная строка
     */
    private static String formatHead(String s) {
        return ANSI.BOLD + ANSI.WHITE + String.format("%1$" + 16 + "s", s) + ANSI.RESET;
    }

    /**
     * Утилитарный метод для {@code toString()}. Красит код статуса в
     * соответствующий цвет:
     *
     * 2xx - зелёный
     * 3xx - жёлтый
     * 4xx и 5xx - красный
     *
     * @param status код статуса HTTP
     * @return отформатированная строка
     */
    private static String formatStatus(int status) {
        if (status / 100 == 2) {
            return ANSI.GREEN + status + ANSI.RESET;
        } else if (status / 100 == 3) {
            return ANSI.YELLOW + status + ANSI.RESET;
        } else if (status / 100 == 4 || status % 100 == 5) {
            return ANSI.RED + status + ANSI.RESET;
        } else {
            return String.valueOf(status);
        }
    }

    /**
     * Преобразует объект в формат строки с информацией о нём
     *
     * @return строка, содержащая всю информацию об объекте
     */
    @Override
    public String toString() {
        return formatHead("IP: ") + ip + '\n' +
                formatHead("Date: ") + dateTime.format(outputFormatter) + '\n' +
                formatHead("Method: ") + method + '\n' +
                formatHead("URL: ") + url + '\n' +
                formatHead("Protocol: ") + protocol + '\n' +
                formatHead("HTTP Status: ") + formatStatus(statusCode) + '\n' +
                formatHead("Response size: ") + size + '\n' +
                formatHead("Client ID: ") + clientId + '\n';
    }

    /**
     * Функциональный класс, преобразующий строку из файла лога в объект типа
     * {@link LogEntry}
     */
    public static class Mapper implements Function<String, LogEntry> {
        /**
         * Функция, преобразующая строку из файла лога в объект типа
         * {@link LogEntry}
         *
         * @param s строка из файла
         * @return объект типа {@link LogEntry} или {@code null}, если такого
         *         не было найдено
         */
        @Override
        public LogEntry apply(String s) {
            Pattern pattern = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s-\\s-\\s\\[(\\d{1,2}/[A-Za-z]{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2}\\s[+-]\\d{2}:\\d{2})]\\s\"([A-Z]*)\\s(/.*?)\\s(.*?)\"\\s(\\d{3})\\s(\\d*)\\sID(\\d*)");
            Matcher matcher = pattern.matcher(s);

            if (matcher.find()) {
                return LogEntry.builder()
                        .ip(matcher.group(1))
                        .dateTime(LocalDateTime.parse(matcher.group(2), inputFormatter))
                        .method(matcher.group(3))
                        .url(matcher.group(4))
                        .protocol(matcher.group(5))
                        .statusCode(Integer.parseInt(matcher.group(6)))
                        .size(Integer.parseInt(matcher.group(7)))
                        .clientId(Integer.parseInt(matcher.group(8)))
                        .build();
            } else {
                return null;
            }
        }
    }
}
