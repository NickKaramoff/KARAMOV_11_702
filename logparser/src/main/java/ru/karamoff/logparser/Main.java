package ru.karamoff.logparser;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.util.PGInterval;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.karamoff.logparser.utils.ANSI;
import ru.karamoff.logparser.utils.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;

class Main {

    private static JdbcTemplate template;

    public static void main(String[] args) {
        Logger.action("Connecting to database");
        long time = System.currentTimeMillis();
        try {
            template = connectToDatabase();
            Logger.success("Connection established. Took " + (System.currentTimeMillis() - time) / 1000.0 + "s.", "Checking if table exists...");

            if (tableExists()) {
                Logger.warning(
                        "Table already exists. If you want to import new data, " +
                                "drop or rename table 'entries'.\n",
                        "Preparing to perform requests...");
            } else {
                Logger.warning("Table doesn't exist.", "Loading and processing file with log entries...");

                time = System.currentTimeMillis();
                ArrayList<LogEntry> entries = getEntries();
                Logger.success("File processed. Took " + (System.currentTimeMillis() - time) / 1000.0 + "s.", "Creating table in database...");

                time = System.currentTimeMillis();
                createTable();
                Logger.success("Table created. Took " + (System.currentTimeMillis() - time) / 1000.0 + "s.", "Writing data...");

                time = System.currentTimeMillis();
                writeData(entries);
                Logger.success("Data written. Took " + (System.currentTimeMillis() - time) / 1000.0 + "s.", "Preparing to perform requests...");
            }

            time = System.currentTimeMillis();
            calculateAndDisplayData();
            System.out.println();
            Logger.success("Data calculated. Took " + (System.currentTimeMillis() - time) / 1000.0 + "s.");
            template.getDataSource().getConnection().close();
            Logger.success("Connection closed.");
        } catch (FileNotFoundException e) {
            Logger.error("File access.log not found!");
        } catch (SQLException e) {
            Logger.error("Database operation failed!", "Additional data:");
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
                "ip VARCHAR(15)," +
                "date_time TIMESTAMP," +
                "method VARCHAR(7)," +
                "url VARCHAR(2000)," +
                "protocol VARCHAR(10)," +
                "status_code SMALLINT," +
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
                    "INSERT INTO entries(ip, date_time, method, url, protocol, status_code, size, client_id) VALUES (?,?,?,?,?,?,?,?);",
                    entry.getIp(),
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

    private static void calculateAndDisplayData() {
        int uniqueClients = template.queryForObject(
                "SELECT count(*) " +
                "FROM (SELECT DISTINCT client_id FROM entries) as unique_clients;",
                (rs, i) -> rs.getInt("count")
        );

        String pageViewStat = template.queryForObject(
                "SELECT min(visits), avg(visits)::NUMERIC(10,3), max(visits) " +
                "FROM " +
                    "(SELECT count(url) as visits" +
                    " FROM entries GROUP BY client_id) as client_visits;",
                (rs, i) ->
                        ANSI.RED + rs.getInt("min") + ANSI.RESET +
                        "/" +
                        ANSI.YELLOW + rs.getDouble("avg") + ANSI.RESET +
                        "/" +
                        ANSI.GREEN + rs.getInt("max") + ANSI.RESET
        );

        int clientsWhoOrdered = template.queryForObject(
                "SELECT count(*) FROM (SELECT DISTINCT client_id FROM entries WHERE url LIKE '%order%') as orders;",
                (rs, i) -> rs.getInt("count")
        );

        double avgOrdersPerDay = template.queryForObject(
                "SELECT avg(orders) " +
                "FROM " +
                    "(SELECT date_time::DATE, count(url) as orders" +
                    " FROM entries WHERE url LIKE '%order%'" +
                    " GROUP BY date_time::DATE) as order_count;",
                (rs, i) -> rs.getDouble("avg")
        );

        PGInterval[] intervals = template.queryForObject(
                "SELECT min(order_t-enter_t), avg(order_t-enter_t), max(order_t-enter_t) " +
                "FROM " +
                    "(SELECT client_id, date_time as order_t" +
                    " FROM entries WHERE url='/order.phtml') as order_t_t " +
                    "JOIN " +
                    "(SELECT client_id, min(date_time) as enter_t" +
                    " FROM entries GROUP BY client_id) as enter_t_t " +
                    "ON order_t_t.client_id=enter_t_t.client_id;",
                (rs, i) -> new PGInterval[]{
                        (PGInterval) rs.getObject("min"),
                        (PGInterval) rs.getObject("avg"),
                        (PGInterval) rs.getObject("max")
                }
        );
        String orderTimeStat =
                "\tmin: " + ANSI.RED +  intervals[0].toString() + "\n" + ANSI.RESET +
                "\tavg: " + ANSI.YELLOW +  intervals[1].toString() + "\n" + ANSI.RESET +
                "\tmax: " + ANSI.GREEN + intervals[2].toString() + ANSI.RESET;

        double avgUsersPerHour = template.queryForObject(
                "SELECT avg(views)::NUMERIC(10,3)\n" +
                "FROM " +
                    "(SELECT date_trunc('hour', date_time), count(DISTINCT client_id) as views" +
                    " FROM entries" +
                    " GROUP BY date_trunc('hour', date_time)) as view_count;",
                (rs, i) -> rs.getDouble("avg")
        );

        int bestBuyer = template.queryForObject(
                "SELECT client_id, count(*) as item_count " +
                "FROM " +
                    "(SELECT client_id, url FROM entries WHERE url like '/addbasket%') as purchases " +
                "GROUP BY client_id " +
                "ORDER BY item_count DESC " +
                "LIMIT 1;",
                (rs, i) -> rs.getInt("client_id")
        );

        int bestBook = template.queryForObject(
                "SELECT split_part(url, '=', 2) as book_id, count(url) as order_count " +
                "FROM entries " +
                "WHERE url LIKE '%addbasket%' " +
                "GROUP BY url " +
                "ORDER BY order_count DESC " +
                "LIMIT 1;",
                (rs, i) -> Integer.parseInt(rs.getString("book_id"))
        );

        System.out.println(wb("Unique clients: ") + uniqueClients);
        System.out.println(wb("Page views per user min/avg/max: ") + pageViewStat);
        System.out.println(wb("Clients who placed an order: ") + clientsWhoOrdered);
        System.out.println(wb("Average orders per day: ") + avgOrdersPerDay);
        System.out.println(wb("Time between first enter and placing order:"));
        System.out.println(orderTimeStat);
        System.out.println(wb("Average users per hour: ") + avgUsersPerHour);
        System.out.println(wb("Client who made the most purchases: ") + bestBuyer);
        System.out.println(wb("Book that was purchased the most: ") + bestBook);
    }

    private static String wb(String message) {
        return ANSI.BOLD + ANSI.WHITE + message + ANSI.RESET;
    }
}
