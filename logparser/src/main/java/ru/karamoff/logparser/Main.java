package ru.karamoff.logparser;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.karamoff.logparser.utils.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private static JdbcTemplate template;

    public static void main(String[] args) {
        Logger.action("Connecting to database");

        try {
            template = connectToDatabase();
            Logger.success("Connection established.", "Checking if table exists...");

            if (tableExists()) {
                Logger.warning(
                        "Table already exists. If you want to import new data, " +
                                "drop or rename table 'entries'.",
                        "Preparing to perform requests...");
            } else {
                Logger.warning("Table doesn't exist.", "Loading file with log entries...");

                ArrayList<LogEntry> entries = getEntries();
                Logger.success("File loaded.", "Processing entries...");
                Logger.success("Entries processed.", "Creating table in database...");

                createTable();
                Logger.success("Table created.", "Writing data...");

                writeData(entries);
                Logger.success("Data written.", "Preparing to perform requests...");
            }

//            TODO: обработать данные с помощью SQL и вывести результаты

        } catch (FileNotFoundException e) {
            Logger.error("File access.log not found!");
        } catch (SQLException e) {
            Logger.error("Database opration failed!", "Additional data:");
            e.printStackTrace();
        }
    }

    /**
     * Устанавливает подключение к базе данных {@code entries}. В этой базе
     * будут храниться все записи логов.
     *
     * @return {@link JdbcTemplate}, который будет выполнять запросы к БД
     * @throws SQLException если подключение не удалось по каким-то причинам
     */
    private static JdbcTemplate connectToDatabase() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("qwerty007");
        dataSource.setURL("jdbc:postgresql://localhost:55432/logparser");
        dataSource.getConnection();
        return new JdbcTemplate(dataSource);
    }

    /**
     * Проверяет наличие таблицы {@code entries}, куда будут записываться
     * данные.
     * Внимание! Метод не проверяет таблицу на соответствие структуры.
     *
     * @return {@code true}, если таблица существует и {@code false}, если нет
     */
    private static boolean tableExists() {
        return template.queryForObject(
                "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'entries') as table_exists;",
                (resultSet, i) -> resultSet.getBoolean("table_exists")
        );
    }

    /**
     * Загружает файл с логами и парсит его, преобразовывая его в список
     * объектов, удобных для чтения и обработки
     *
     * @return список объектов типа {@link LogEntry}, соответствующих всем
     *         записям в файле лога
     * @throws FileNotFoundException если файл access.log не был найден
     */
    private static ArrayList<LogEntry> getEntries() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(
                new FileReader(
                        new File("access.log")));
        return reader
                .lines()
                .map(new LogEntry.Mapper())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Создаёт таблицу {@code entries} в базе данных
     */
    private static void createTable() {
        template.execute("CREATE TABLE entries " +
                "(" +
                "id BIGSERIAL PRIMARY KEY," +
                "datetime TIMESTAMP," +
                "method VARCHAR(7)," +
                "url VARCHAR(2000)," +
                "protocol VARCHAR(32)," +
                "status_code NUMERIC(3)," +
                "size INTEGER," +
                "client_id INTEGER" +
                ");");
    }

    /**
     * Записывает объекты {@link LogEntry} в базу данных
     *
     * @param entries список объектов типа {@link LogEntry}
     */
    private static void writeData(ArrayList<LogEntry> entries) {
        for (LogEntry entry : entries) {
            template.update(
                    "INSERT INTO entries(datetime, method, url, protocol, status_code, size, client_id) VALUES (?,?,?,?,?,?,?);",
                    Timestamp.valueOf(entry.getDateTime()),
                    entry.getMethod(),
                    entry.getUrl(),
                    entry.getProtocol(),
                    entry.getStatusCode(),
                    entry.getSize(),
                    entry.getClientId()
            );
        }
    }
}
